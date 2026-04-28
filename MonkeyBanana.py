from collections import deque

# Positions (line: 0 → 4)
positions = [0, 1, 2, 3, 4]
banana_pos = 4

start = (0, 2, False, False)  # (monkey, box, on_box, has_banana)

def get_next_states(state):
    m, b, on_box, has_banana = state
    next_states = []

    if has_banana:
        return []

    # Walk (left or right)
    if not on_box:
        if m - 1 in positions:
            next_states.append((m - 1, b, False, False))
        if m + 1 in positions:
            next_states.append((m + 1, b, False, False))

    # Push box
    if m == b and not on_box:
        if m - 1 in positions:
            next_states.append((m - 1, b - 1, False, False))
        if m + 1 in positions:
            next_states.append((m + 1, b + 1, False, False))

    # Climb box
    if m == b and not on_box:
        next_states.append((m, b, True, False))

    # Grasp banana
    if m == banana_pos and on_box:
        next_states.append((m, b, True, True))

    return next_states


def bfs(start):
    queue = deque([(start, [])])
    visited = set()

    while queue:
        state, path = queue.popleft()

        if state in visited:
            continue

        visited.add(state)

        if state[3]:  # has banana
            return path + [state]

        for nxt in get_next_states(state):
            queue.append((nxt, path + [state]))

    return None


# Run
solution = bfs(start)

for step in solution:
    print(step)
