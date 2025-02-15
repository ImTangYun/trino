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
package io.trino.plugin.hive.metastore;

import io.trino.plugin.hive.HiveColumnStatisticType;
import io.trino.plugin.hive.HiveType;
import io.trino.plugin.hive.PartitionStatistics;
import io.trino.plugin.hive.acid.AcidTransaction;
import io.trino.plugin.hive.metastore.HivePrivilegeInfo.HivePrivilege;
import io.trino.spi.connector.RelationType;
import io.trino.spi.connector.SchemaTableName;
import io.trino.spi.function.LanguageFunction;
import io.trino.spi.predicate.TupleDomain;
import io.trino.spi.security.RoleGrant;
import io.trino.spi.type.Type;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

public class UnimplementedHiveMetastore
        implements HiveMetastore
{
    @Override
    public Optional<Database> getDatabase(String databaseName)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> getAllDatabases()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Table> getTable(String databaseName, String tableName)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<HiveColumnStatisticType> getSupportedColumnStatistics(Type type)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public PartitionStatistics getTableStatistics(Table table)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Map<String, PartitionStatistics> getPartitionStatistics(Table table, List<Partition> partitions)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateTableStatistics(String databaseName,
            String tableName,
            AcidTransaction transaction,
            Function<PartitionStatistics, PartitionStatistics> update)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updatePartitionStatistics(Table table, Map<String, Function<PartitionStatistics, PartitionStatistics>> updates)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> getAllTables(String databaseName)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<List<SchemaTableName>> getAllTables()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Map<String, RelationType> getRelationTypes(String databaseName)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Map<SchemaTableName, RelationType>> getRelationTypes()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> getTablesWithParameter(String databaseName, String parameterKey, String parameterValue)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> getAllViews(String databaseName)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<List<SchemaTableName>> getAllViews()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void createDatabase(Database database)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void dropDatabase(String databaseName, boolean deleteData)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void renameDatabase(String databaseName, String newDatabaseName)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setDatabaseOwner(String databaseName, HivePrincipal principal)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setTableOwner(String databaseName, String tableName, HivePrincipal principal)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void createTable(Table table, PrincipalPrivileges principalPrivileges)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void dropTable(String databaseName, String tableName, boolean deleteData)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void replaceTable(String databaseName, String tableName, Table newTable, PrincipalPrivileges principalPrivileges)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void renameTable(String databaseName, String tableName, String newDatabaseName, String newTableName)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void commentTable(String databaseName, String tableName, Optional<String> comment)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void commentColumn(String databaseName, String tableName, String columnName, Optional<String> comment)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void addColumn(String databaseName, String tableName, String columnName, HiveType columnType, String columnComment)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void renameColumn(String databaseName, String tableName, String oldColumnName, String newColumnName)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void dropColumn(String databaseName, String tableName, String columnName)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Partition> getPartition(Table table, List<String> partitionValues)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<List<String>> getPartitionNamesByFilter(String databaseName,
            String tableName,
            List<String> columnNames,
            TupleDomain<String> partitionKeysFilter)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Map<String, Optional<Partition>> getPartitionsByNames(Table table, List<String> partitionNames)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void addPartitions(String databaseName, String tableName, List<PartitionWithStatistics> partitions)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void dropPartition(String databaseName, String tableName, List<String> parts, boolean deleteData)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void alterPartition(String databaseName, String tableName, PartitionWithStatistics partition)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<HivePrivilegeInfo> listTablePrivileges(String databaseName, String tableName, Optional<String> tableOwner, Optional<HivePrincipal> prestoPrincipal)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void grantTablePrivileges(String databaseName, String tableName, String tableOwner, HivePrincipal grantee, HivePrincipal grantor, Set<HivePrivilege> privileges, boolean grantOption)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void revokeTablePrivileges(String databaseName, String tableName, String tableOwner, HivePrincipal grantee, HivePrincipal grantor, Set<HivePrivilege> privileges, boolean grantOption)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void createRole(String role, String grantor)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void dropRole(String role)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<String> listRoles()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void grantRoles(Set<String> roles, Set<HivePrincipal> grantees, boolean adminOption, HivePrincipal grantor)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void revokeRoles(Set<String> roles, Set<HivePrincipal> grantees, boolean adminOption, HivePrincipal grantor)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<RoleGrant> listRoleGrants(HivePrincipal principal)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean functionExists(String databaseName, String functionName, String signatureToken)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<LanguageFunction> getFunctions(String databaseName)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<LanguageFunction> getFunctions(String databaseName, String functionName)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void createFunction(String databaseName, String functionName, LanguageFunction function)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void replaceFunction(String databaseName, String functionName, LanguageFunction function)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void dropFunction(String databaseName, String functionName, String signatureToken)
    {
        throw new UnsupportedOperationException();
    }
}
