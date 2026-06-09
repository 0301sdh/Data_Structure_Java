# Java Data Structure Study

자료구조를 공부하며 작성한 예제 코드 저장소입니다.

## Progress

- [O] Array
- [O] ArrayList
- [O] LinkedList
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

## Goal

- 자료구조 이해
- Git/GitHub 활용