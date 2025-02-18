## ecommerce-purchaseOrder-payment-system

### 요구사항 분석

#### 1. 상품 주문
- 상품 주문은 최소 1개 이상 주문해야 한다.
- 한번의 주문에 다수의 상품을 n개 구매할 수 있다.
- 한번의 주문에 동일의 상품을 n개 구매할 수 있다.
- 결제 수단을 카드 결제만 가능하며, 추후 결제 수단은 추가될 수 없다.

#### 2. 상품 결제
- "주문 완료"된 건에 대해서만 결제를 진행할 수 있다.

#### 3. 주문 취소
- "구매 확정"된 상품은 주문 취소할 수 없다.
- 결제 취소 진행은 상품이 재입고된 이후에 진행된다.
- 결제 취소는 부분 취소 및 전체 취소가 가능하다.

#### 4. 주문 || 결제 정산
- 결제일 + 영업일 기준 3day 건에 대해서 정산이 이루어진다.
- Tosspayments에서 제공하는 정산 API를 호출해서 정산 완료된 주문 건에 대해서 데이터를 적재한다.
- 방법 1. 개발 환경에서 Mockito로 비즈니스 로직만 테스트하고 운영 환경에 배포 후, 테스트를 한다.
- 방법 2. Mock Server를 이용해서 API까지 테스트를 한다.

### 이벤트 스토밍

#### 1. 도메인 이벤트 (Domain event) 도출
#### 2. 이벤트에 따른 정책(Policy) 도출
#### 3. Command와 Actor 도출
#### 4. Aggregate 매핑

### 프로젝트 구조
#### 1. Presentation
        사용자의 요청을 처리하고 사용자에게 정보를 보여주는 역할을 한다.
        controller역할을 한다고 생각할 수도 있지만 presentation은 controller처럼 http에만 종속된는 것이 아니라
        외부 시스템일 수도 있다. 여기서 외부 시스템은 Front Server가 될수도 있고 OpenApi 형태로 다른 특정한 백엔드 시스템이 우리한테 Request를 요청하는
        시스템이 될 수도 있다.
#### 2. Application
        사용자가 요청한 기능을 실행한다. 업무 로직을 직접 구현하지 않으며 도메인 계층을 조합해서 기능을 실행한다.
        Service단이라고 생각하면 되는데 핵심 Service 로직은 도메인에서 다 구현하고
        그냥 도메인에 있는 로직을 조합하는 곳이라고 생각하면 쉬울 것 같다.
        정리하자면 기능 구현을 책임지고 있는 것이 아니라, 만들어져 있는 기능을 조합해서 온전한 기능을 구현하는 계층이다.
#### 3. Domain
        시스템(비즈니스)이 제공해야 하는 도메인 규칙을 구현한다.
        비즈니스의 핵심 규칙을 정의하고 구현하는 곳이다.
#### 4. Infrastructure
        application의 context 경계를 벗어나는 외부 시스템과의 연동을 구현한다.
        인프라인스트럭처 및 도메인 계층을 제외한 나머지 계층에서는 저수준 모듈이 아닌 고수준 모듈에 의존해서
        코드를 작성하고 도메인과 인프라인스트럭처 계층에서는 저수준 모듈을 구현하여 실제 기능을 제공한다는 것이
        다른 계층과의 차이점을 가진다.

** DDD 는 중복을 감수하고 가는 것이다 ??

### 토스 결제 API 정리
https://docs.google.com/spreadsheets/d/1ts5o2Wm6C4avU44-AHIwWIV1PhUVz4-10soBuVSq3ms/edit?usp=sharing

### DIP
 고수준과 저수준 모듈 (DIP)
 우리 프로젝트에서는 구현체(저수준 모듈)에 직접 의존하지 않는다.
 추상체 or 인터페이스에 의존하여 기능을 완성시킨다.
 
### TDD

#### 메소드명 컨벤션
 [테스트 진행할 메소드 명] _ [결과값] _ [테스트 케이스에 대한 시나리오]
 이런 식으로 작성하면 된다.
 ex ) 하나의 주문에 동일한 상품을 n개 구매할 수 없다.
 verifyDuplicatedOrderItemId_True_NotDuplicateProductId()
 
#### JPA TEST
 @DataJpaTest : JPA 관련 전용 테스트로 테스트 진행 시 관련 Bean만 띄운다.
 @AutoConfigureTestDataBase : 개발 DB , 운영 DB 를 사용하지 않고 TEST용 DB(H2)를 사용해서
  테스트를 진행할 수 있게 설정 해주는 어노테이션
 
#### 순서
 1. 일단 제일 먼저 도메인과 response, request dto, 공통 코드, 인프라 관련 틀을 먼저 코드를 작성해주고
 2. 요구사항에 따라서 TDD 를 작성해주면서 해당 관련 비지니스 로직들을 작성해주는게 편한 것 같다 (controller, port, service, repository 등등)

#### TransactionType에 관하여
- transactionType 결제 타입은 지금 당장은 카드 결제만 고려를 했지만 앞으로 카드, 계좌이체, 가상계좌 등등
다양한 결제 타입의 확장성을 고려한 개발을 해야한다. 따라서 domain에 TransactionType 이라는 부모클래스를 만들고
cardPayment,AccountPayment 등등 여러개의 도메인을 만들어준 다음 transactionType을 상속받는다면 repository를 구현할 때에도
결제 방식 별로 로직을 객체지향적으로 구분할 수 있다는 장점을 가진다.

#### Kafka에 관하여