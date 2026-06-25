# Java Data Structure Study

자료구조를 공부하며 작성한 예제 코드 저장소입니다.

## Progress

- [O] Array
- [O] ArrayList
- [O] LinkedList
- [O] Double LinkedList
- [O] Circular LinkedList
- [O] Stack
- [O] Queue
- [O] Deque
- [O] HashMap
- [O] HashSet

---

## MyHashSet 구현 및 학습 내용

### 1. 주요 특징 및 핵심 설계
* Chaining(연결 리스트) 기반 해시셋: 배열 버킷 내부에서 충돌(Collision)이 발생할 경우, 노드의 연결 리스트(next)를 활용하여 데이터를 유연하게 확장하는 체이닝 기법을 직접 구현했습니다.
* null 원소의 원천 차단: 내부 원소를 다룰 때 발생할 수 있는 NullPointerException을 방지하고 코드 가독성을 높이기 위해, add, remove, contains 메서드의 입구에서 null 입력을 감지하고 예외를 던지도록 설계했습니다.

### 2. 설계 변경 내용: 리턴 타입 수정 (void에서 boolean으로 변경)
원래는 추가(put)와 삭제(remove) 성공 여부를 반환하지 않는 void 형태였으나, 실제 Java 표준 스펙에 맞춰 boolean 타입을 반환하도록 리팩토링했습니다.

* 변경 이유 (외부 사용자 관점의 효율성):
  * 만약 void였다면, 외부에서 데이터가 이미 존재하는지 파악하기 위해 contains()를 먼저 호출하고 put()을 호출해야 하므로 총 2번의 탐색이 필요합니다.
  * boolean으로 변경함으로써, 내부적으로 중복 체크를 위해 연결 리스트를 쭉 훑는 김에 그 결과를 리턴값으로 돌려줄 수 있게 되었습니다.
  * 덕분에 외부 사용자는 단 한 번의 탐색(O(1))으로 '저장'과 '중복 체크'를 동시에 처리하는 효율적인 코드를 작성할 수 있습니다.

---

## HashSet 시간 복잡도 (Time Complexity)

| 연산 (Method) | 평균 시간 복잡도 (Average) | 최악의 시간 복잡도 (Worst) | 설명 |
| :--- | :--- | :--- | :--- |
| **추가 (add)** | O(1) | O(N) | 해시 충돌이 없어 바로 저장 시 O(1), 최악의 경우 모든 데이터가 한 버킷에 몰리면 리스트를 뒤져야 하므로 O(N). |
| **삭제 (remove)** | O(1) | O(N) | 원하는 해시 방으로 바로 찾아가 삭제 시 O(1). |
| **조회 (contains)** | O(1) | O(N) | 데이터의 존재 여부를 해시 기반으로 즉시 판단. |

* Resize 성능: 데이터 개수가 버킷 용량의 75%(LOAD_FACTOR = 0.75f)를 넘어서면, 배열의 크기를 2배로 늘리고 기존 데이터를 재배치(resize)하는 연산이 수행됩니다. 이 순간에는 O(N)이 소요됩니다

---

## Goal


- 자료구조 이해
- Git/GitHub 활용