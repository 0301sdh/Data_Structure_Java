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

## HashMap 정리 (2026-06-20)

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

## Goal

- 자료구조 이해
- Git/GitHub 활용