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
- [O] HashMap
- [O] HashSet

## MyLinkedList

2026-06-09에 제네릭 기반 단일 연결 리스트(Singly Linked List)를 직접 구현했습니다.
### 주의사항
- [insert: 빈 리스트에 삽입할 때 tail을 따로 처리해야 함](MyLinkedList.java#L78-L80)
- [remove: 삭제로 빈 리스트가 되면 tail을 따로 처리해야 함](MyLinkedList.java#L108-L110)

### 구현한 메서드

| 메서드 | 설명 | 시간복잡도 |
|--------|------|-----------|
| `add(T data)` | 맨 뒤에 추가 | O(1) |
| `get(int index)` | 인덱스로 값 조회 | O(n) |
| `contains(T data)` | 값 포함 여부 확인 | O(n) |
| `insert(int index, T data)` | 특정 위치까지 탐색 후 삽입 | O(n) |
| `remove(int index)` | 특정 위치까지 탐색 후 삭제 | O(n) |
| `size()` | 리스트 크기 반환 | O(1) |
| `isEmpty()` | 비어있는지 확인 | O(1) |

- `head`, `tail` 포인터를 사용하여 `add()`는 O(1)로 동작
- 단일 연결 리스트이므로 역방향 탐색 불가

## MyDoubleLinkedList

2026-06-10에 제네릭 기반 이중 연결 리스트(Doubly Linked List)를 직접 구현했습니다.

### 단일 연결 리스트와의 차이점
- 각 노드가 `next`와 `prev` 두 개의 포인터를 가짐
- 역방향 탐색이 가능 (`toReverseString`으로 검증)
- `get()` 시 index가 size/2보다 크면 tail에서 prev로 탐색하여 효율 향상

### 주의사항
- [remove: 맨 앞 삭제 시 원소가 1개면 head가 null이 되므로 prev 접근 전에 null 체크 필요](MyDoubleLinkedList.java#L126-L133)
- [remove: 중간 삭제 시 next를 먼저 바꾸면 prev 연결이 꼬임 — 순서 주의](MyDoubleLinkedList.java#L135-L141)
- insert/remove 후 prev 연결을 빠뜨리기 쉬움 → toReverseString으로 검증

### 구현한 메서드

| 메서드 | 설명 | 시간복잡도 |
|--------|------|-----------|
| `add(T data)` | 맨 뒤에 추가 | O(1) |
| `get(int index)` | 인덱스로 값 조회 (절반 기준 양방향 탐색) | O(n/2) |
| `contains(T data)` | 값 포함 여부 확인 | O(n) |
| `insert(int index, T data)` | 특정 위치에 삽입 | O(n) |
| `remove(int index)` | 특정 위치 삭제 | O(n) |
| `size()` | 리스트 크기 반환 | O(1) |
| `isEmpty()` | 비어있는지 확인 | O(1) |
| `toReverseString()` | 역방향 출력 (prev 연결 검증용) | O(n) |

## MyCircularLinkedList

2026-06-10에 제네릭 기반 원형 연결 리스트(Circular Linked List)를 직접 구현했습니다.

### 일반 연결 리스트와의 차이점
- tail.next가 null이 아닌 head를 가리켜 순환 구조를 형성
- 노드가 1개일 때 자기 자신을 가리킴 (head.next == head)
- 끝이 없으므로 순환 횟수를 지정하여 출력 가능 (`toCircularString`)

### 주의사항
- [remove: 원소 1개일 때 head = head.next 하면 자기 자신을 가리키므로 삭제 안 됨 — size == 1 분기 필요](MyCircularLinkedList.java#L114-L122)
- [insert: 빈 리스트에 삽입 시 tail.next = head 순환 연결 필수](MyCircularLinkedList.java#L78-L82)
- add/insert/remove 후 반드시 tail.next = head 유지 확인

### 구현한 메서드

| 메서드 | 설명 | 시간복잡도 |
|--------|------|-----------|
| `add(T data)` | 맨 뒤에 추가 | O(1) |
| `get(int index)` | 인덱스로 값 조회 | O(n) |
| `contains(T data)` | 값 포함 여부 확인 | O(n) |
| `insert(int index, T data)` | 특정 위치에 삽입 | O(n) |
| `remove(int index)` | 특정 위치 삭제 | O(n) |
| `size()` | 리스트 크기 반환 | O(1) |
| `isEmpty()` | 비어있는지 확인 | O(1) |
| `toCircularString(int n)` | n바퀴 순환 출력 | O(n * size) |

## Goal

- 자료구조 이해
- Git/GitHub 활용