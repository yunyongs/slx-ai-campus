package com.bear.section03.column;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/*
 * [3. Column 과 기본 타입 매핑] 실습용 엔티티
 *
 * 필드마다 "어떤 @Column 속성 → DDL 에서 어떻게 → INSERT/UPDATE 에서 어떻게" 를 주석으로 적었다.
 * C_ColumnMappingTest 의 첫 테스트가 실제로 만들어진 컬럼 구조를 콘솔에 표로 찍어 준다.
 */
@Entity(name = "LabProduct")
@Table(
        name = "lab_product",
        // 여러 컬럼 "조합"의 유일성 → @Table 의 uniqueConstraints (단일 컬럼은 @Column(unique = true))
        uniqueConstraints = @UniqueConstraint(
                name = "uk_lab_product_name_maker",
                columnNames = {"product_name", "maker"}
        )
)
public class LabProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_no")
    private Long productNo;

    // DDL: varchar(20) not null + unique  /  같은 코드를 두 번 넣으면 DB 가 거부한다
    @Column(name = "product_code", nullable = false, unique = true, length = 20)
    private String productCode;

    // DDL: varchar(50) not null  /  null 을 넣으면 저장 단계에서 예외
    @Column(name = "product_name", nullable = false, length = 50)
    private String productName;

    @Column(name = "maker", length = 30)
    private String maker;

    // BigDecimal → decimal(10,2): 전체 10자리 중 소수 2자리 (precision, scale 은 BigDecimal 같은 숫자 타입에 쓰는 속성)
    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;

    // columnDefinition: SQL 을 직접 적는다 (MySQL 전용 문법이 섞일 수 있음)
    // insertable = false → INSERT 문에서 이 컬럼이 "빠진다" → DB 기본값 '미입력' 이 들어간다
    @Column(name = "memo", columnDefinition = "varchar(200) default '미입력'", insertable = false)
    private String memo;

    // 같은 기본값을 줬지만 insertable 은 기본(true) → INSERT 에 note = null 이 "명시적으로" 들어간다 → 기본값 적용 안 됨
    @Column(name = "note", columnDefinition = "varchar(200) default '미입력'")
    private String note;

    // updatable = false → UPDATE 문에서 이 컬럼이 빠진다 (자바 필드 값은 바꿀 수 있지만 DB 에는 반영 안 됨)
    @Column(name = "registered_by", length = 20, updatable = false)
    private String registeredBy;

    // @Transient: 매핑 제외 → 컬럼이 아예 안 생기고, 저장도 조회도 안 된다 (@Column 을 같이 쓰지 않는다)
    @Transient
    private String displayName;

    // java.time 타입: @Temporal 없이 그대로 → date / datetime(6)
    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // 옛날 방식 java.util.Date + @Temporal (Jakarta Persistence 3.2 에서 사용 중단 권고 → 컴파일 시 경고가 보일 수 있다)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "legacy_date")
    private Date legacyDate;

    public LabProduct() {}

    public LabProduct(String productCode, String productName, String maker, BigDecimal price) {
        this.productCode = productCode;
        this.productName = productName;
        this.maker = maker;
        this.price = price;
    }

    public Long getProductNo() { return productNo; }
    public String getProductCode() { return productCode; }
    public String getProductName() { return productName; }
    public String getMaker() { return maker; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public String getMemo() { return memo; }
    public void setMemo(String memo) { this.memo = memo; }
    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
    public String getRegisteredBy() { return registeredBy; }
    public void setRegisteredBy(String registeredBy) { this.registeredBy = registeredBy; }
    public String getDisplayName() { return displayName; }
    public void setDisplayName(String displayName) { this.displayName = displayName; }
    public LocalDate getReleaseDate() { return releaseDate; }
    public void setReleaseDate(LocalDate releaseDate) { this.releaseDate = releaseDate; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public Date getLegacyDate() { return legacyDate; }
    public void setLegacyDate(Date legacyDate) { this.legacyDate = legacyDate; }

    @Override
    public String toString() {
        return "LabProduct{productNo=" + productNo + ", productCode='" + productCode + "', productName='" + productName
                + "', price=" + price + ", memo='" + memo + "', note='" + note + "', registeredBy='" + registeredBy
                + "', displayName='" + displayName + "', releaseDate=" + releaseDate + ", createdAt=" + createdAt + "}";
    }
}
