/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.trino.plugin.iceberg;

import io.trino.filesystem.Location;
import io.trino.filesystem.TrinoFileSystem;
import io.trino.plugin.hive.metastore.Table;
import io.trino.plugin.hive.metastore.file.FileHiveMetastore;
import io.trino.testing.AbstractTestQueryFramework;
import io.trino.testing.DistributedQueryRunner;
import io.trino.testing.MaterializedResult;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static com.google.common.io.MoreFiles.deleteRecursively;
import static com.google.common.io.RecursiveDeleteOption.ALLOW_INSECURE;
import static io.trino.plugin.hive.TableType.EXTERNAL_TABLE;
import static io.trino.plugin.hive.metastore.file.TestingFileHiveMetastore.createTestingFileHiveMetastore;
import static io.trino.plugin.iceberg.DataFileRecord.toDataFileRecord;
import static io.trino.plugin.iceberg.IcebergTestUtils.getFileSystemFactory;
import static io.trino.testing.TestingConnectorSession.SESSION;
import static io.trino.testing.TestingNames.randomNameSuffix;
import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@TestInstance(PER_CLASS)
public class TestIcebergTableWithExternalLocation
        extends AbstractTestQueryFramework
{
    private FileHiveMetastore metastore;
    private File metastoreDir;
    private TrinoFileSystem fileSystem;

    @Override
    protected DistributedQueryRunner createQueryRunner()
            throws Exception
    {
        metastoreDir = Files.createTempDirectory("test_iceberg").toFile();
        metastore = createTestingFileHiveMetastore(metastoreDir);

        return IcebergQueryRunner.builder()
                .setMetastoreDirectory(metastoreDir)
                .build();
    }

    @BeforeAll
    public void initFileSystem()
    {
        fileSystem = getFileSystemFactory(getDistributedQueryRunner()).create(SESSION);
    }

    @AfterAll
    public void tearDown()
            throws IOException
    {
        deleteRecursively(metastoreDir.toPath(), ALLOW_INSECURE);
    }

    @Test
    public void testCreateAndDrop()
            throws IOException
    {
        String tableName = "test_table_external_create_and_drop";
        File tempDir = getDistributedQueryRunner().getCoordinator().getBaseDataDir().toFile();
        String tempDirPath = tempDir.toURI().toASCIIString() + randomNameSuffix();
        assertQuerySucceeds(format("CREATE TABLE %s ( x bigint) WITH (location = '%s')", tableName, tempDirPath));
        assertQuerySucceeds(format("INSERT INTO %s VALUES (1), (2), (3)", tableName));

        Table table = metastore.getTable("tpch", tableName).orElseThrow();
        assertThat(table.getTableType()).isEqualTo(EXTERNAL_TABLE.name());
        Location tableLocation = Location.of(table.getStorage().getLocation());
        assertThat(fileSystem.newInputFile(tableLocation).exists())
                .describedAs("The directory corresponding to the table storage location should exist")
                .isTrue();
        MaterializedResult materializedResult = computeActual("SELECT * FROM \"test_table_external_create_and_drop$files\"");
        assertThat(materializedResult.getRowCount()).isEqualTo(1);
        DataFileRecord dataFile = toDataFileRecord(materializedResult.getMaterializedRows().get(0));
        Location dataFileLocation = Location.of(dataFile.getFilePath());
        assertThat(fileSystem.newInputFile(dataFileLocation).exists())
                .describedAs("The data file should exist")
                .isTrue();

        assertQuerySucceeds(format("DROP TABLE %s", tableName));
        assertThat(metastore.getTable("tpch", tableName)).as("Table should be dropped").isEmpty();
        assertThat(fileSystem.newInputFile(dataFileLocation).exists())
                .describedAs("The data file should have been removed")
                .isFalse();
        assertThat(fileSystem.newInputFile(tableLocation).exists())
                .describedAs("The directory corresponding to the dropped Iceberg table should be removed as we don't allow shared locations.")
                .isFalse();
    }
}
