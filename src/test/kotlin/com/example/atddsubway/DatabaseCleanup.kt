package com.example.atddsubway

import org.springframework.beans.factory.InitializingBean
import org.springframework.stereotype.Component
import java.util.stream.Collectors

@Component
class DatabaseCleanup : InitializingBean {
    @PersistenceContext
    private val entityManager: EntityManager? = null
    private var tableNames: MutableList<String>? = null
    override fun afterPropertiesSet() {
        val entities: Set<EntityType<*>> = entityManager?.getMetamodel()?.getEntities() ?: return
        tableNames = entities.stream()
            .filter(Predicate<EntityType<*>> { e: EntityType<*> -> isEntity(e) && hasTableAnnotation(e) })
            .map<String>(Function<EntityType<*>, String> { e: EntityType<*> ->
                val tableName: String = e.getJavaType().getAnnotation(Table::class.java).name
                if (tableName.isBlank()) CaseFormat.UPPER_CAMEL.to(
                    CaseFormat.LOWER_UNDERSCORE,
                    e.getName()
                ) else tableName
            })
            .collect(Collectors.toList<String>())
        val entityNames = entities.stream()
            .filter(Predicate<EntityType<*>> { e: EntityType<*> -> isEntity(e) && !hasTableAnnotation(e) })
            .map<String>(Function<EntityType<*>, String> { e: EntityType<*> ->
                CaseFormat.UPPER_CAMEL.to(
                    CaseFormat.LOWER_UNDERSCORE,
                    e.getName()
                )
            })
            .toList()
        tableNames?.addAll(entityNames)
    }

    private fun isEntity(e: EntityType<*>): Boolean {
        return null != e.getJavaType().getAnnotation(Entity::class.java)
    }

    private fun hasTableAnnotation(e: EntityType<*>): Boolean {
        return null != e.getJavaType().getAnnotation(Table::class.java)
    }

    @Transactional
    fun execute() {
        /** TEST용 DB는 로컬에서 실행할 것!*/
//        entityManager?.flush()
//        entityManager?.createNativeQuery("SET FOREIGN_KEY_CHECKS = 0")?.executeUpdate()
//        for (tableName in tableNames!!) {
//            if (tableName.equals("right", ignoreCase = true)) {
//                entityManager?.createNativeQuery("DELETE FROM $tableName")?.executeUpdate()
//            } else {
//                entityManager?.createNativeQuery("TRUNCATE TABLE $tableName")?.executeUpdate()
//            }
//            entityManager?.createNativeQuery("ALTER TABLE $tableName AUTO_INCREMENT = 1")?.executeUpdate()
//        }
//        entityManager?.createNativeQuery("SET FOREIGN_KEY_CHECKS = 1")?.executeUpdate()
    }
}
