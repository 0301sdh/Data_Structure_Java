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

## HashMap 구현 (2026-06-20)

### 구조
- 배열(bucket) + 연결 리스트(chaining) 로 구현
- 해시 함수로 key의 hashCode를 버킷 인덱스로 변환
- 해시 충돌 시 같은 버킷에 연결 리스트로 체이닝

### 핵심 개념
- **해시 함수**: `hashCode() ^ (hashCode >>> 16)` 로 상위 비트를 섞어 충돌을 줄임
- **인덱스 계산**: `h & (length - 1)` — 나머지 연산(%)보다 빠름 (단, 배열 크기가 2의 거듭제곱일 때만 가능)
- **Load Factor (0.75)**: `size > capacity * 0.75` 이면 resize (배열 2배 확장 후 재배치)
- **resize 이유**: 체이닝이 길어지면 O(n)에 가까워지므로, 버킷을 늘려서 노드를 분산시킴
- **hashCode 규약**: 같은 값 -> 항상 같은 해시코드  / 다른 값 -> 같은 해시코드 가능 (충돌)

### 시간 복잡도

| 연산 | 평균 | 최악 (모든 키가 같은 버킷에 충돌) |
|------|------|------|
| put | O(1) | O(n) |
| get | O(1) | O(n) |
| remove | O(1) | O(n) |
| containsKey | O(1) | O(n) |
| resize | O(n) | O(n) |

### 기타 메모
- Java에서 `String + int` 하면 자동으로 문자열 결합됨 "key" + 1 -> "key1" 이 됨
- 내부 클래스에서 바깥 제네릭 타입과 이름 충돌 시 `static class`로 선언-> Node<K,V>를 static class 로 선언해서 해결
- Java는 Garbage Collector이 있어서 remove 시 노드를 null 처리할 필요 없음 (참조 끊기면 자동 회수)

## HashMap - Open Addressing 구현 (2026-06-23)

### 구조
- 배열에 key, value를 직접 저장 (연결 리스트 없음)
- 해시 충돌 시 다음 슬롯으로 이동하며 빈 자리를 찾음 (Linear Probing)

### 핵심 개념
- **Linear Probing**: 충돌 시 `(index + 1) % capacity`로 다음 슬롯 탐색
- **Load Factor (0.5)**: Chaining(0.75)보다 낮게 유지 — 슬롯이 꽉 찰수록 탐색 거리가 급격히 늘어나기 때문
- **deleted 표시**: 삭제 시 슬롯을 그냥 비우면 탐색 체인이 끊김 → `deleted[index] = true`로 표시하고 건너뛰며 탐색 계속
- **firstDeleted (deleted 재활용)**: 탐색 중 만난 첫 번째 deleted 위치를 기억해두고, 새 데이터 삽입 시 해당 자리에 저장
- **슬롯 3가지 상태**: 사용 중(`keys[i] != null`) / deleted(`deleted[i] == true`) / 빈 슬롯(`keys[i] == null && !deleted[i]`)
- **resize 시 deleted 정리**: 살아있는 데이터만 다시 put하므로 deleted가 자연스럽게 제거됨

### Chaining vs Open Addressing 비교

| 항목 | Chaining (MyHashMap) | Open Addressing (MyHashMap2) |
|------|------|------|
| 충돌 해결 | 연결 리스트 | Linear Probing |
| Load Factor | 0.75 | 0.5 |
| 삭제 | 노드 제거 | deleted 표시 필요 |
| 메모리 | Node 객체 추가 할당 | 배열만 사용 |

### 시간 복잡도

| 연산 | 평균 | 최악 (모든 키가 같은 해시값) |
|------|------|------|
| put | O(1) | O(n) |
| get | O(1) | O(n) |
| remove | O(1) | O(n) |
| containsKey | O(1) | O(n) |
| resize | O(n) | O(n) |

### 기타 메모
- `null.equals(key)`는 NPE 발생 → findIndex에서 `!deleted[index]` 체크로 방지


## Goal

- 자료구조 이해
- Git/GitHub 활용