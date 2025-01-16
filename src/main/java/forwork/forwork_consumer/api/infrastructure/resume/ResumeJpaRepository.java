//package forwork.forwork_consumer.api.infrastructure.resume;
//
//import jakarta.persistence.LockModeType;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Lock;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//import java.util.List;
//import java.util.Optional;
//
//public interface ResumeJpaRepository extends JpaRepository<ResumeEntity, Long>{
//    @Modifying
//    @Query("UPDATE ResumeEntity r SET r.salesQuantity = r.salesQuantity + :quantity WHERE r.id = :resumeId")
//    void updateSalesQuantity(@Param("resumeId") Long resumeId, @Param("quantity") Integer quantity);
//}
