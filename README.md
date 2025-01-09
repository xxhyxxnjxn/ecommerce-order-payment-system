## ecommerce-order-payment-system

### 요구사항 분석

#### 1. 상품 주문
- 상품 주문은 최소 1개 이상 주문해야 한다.
- 한번의 주문에 다수의 상품을 n개 구매할 수 있다.
- 결제 수단을 카드 결제만 가능하며, 추후 결제 수단은 추가될 수 없다.

#### 2. 상품 결제
- "주문 완료"된 건에 대해서만 결제를 진행할 수 있다.

#### 3. 주문 취소
- "구매 확정"된 상품은 주문 취소할 수 없다.
- 결제 취소 진행은 상품이 재입고된 이후에 진행된다.
- 결제 취소는 부분 취소 및 전체 취소가 가능하다.

#### 4. 주문 || 결제 정산
- 결제일 + 영업일 기준 3day 건에 대해서 정산이 이루어진다.

### 이벤트 스토밍

#### 1. 도메인 이벤트 (Domain event) 도출
#### 2. 이벤트에 따른 정책(Policy) 도출
#### 3. Command와 Actor 도출
#### 4. Aggregate 매핑

### 프로젝트 구조
#### 1. Presentation
        사용자의 요청을 처리하고 사용자에게 정보를 보여주는 역할을 한다.
        controller역할을 한다고 생각할 수도 있지만 presentation은 controller처럼 http에만 종속된는 것이 아니라
        외부 시스템일 수도 있다. 여기서 외부 시스템은 Front Server가 될수도 있고 OpenApi 형태로 Request를 요청하는
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
