from collections import deque

# Define initial state
start = ('A', 'B', False, False)  
goal = True  # has_banana = True

def get_next_states(state):
    m_pos, b_pos, on_box, has_banana = state
    states = []

    if has_banana:
        return []

    # Walk
    for pos in ['A', 'B', 'C']:
        if pos != m_pos and not on_box:
            states.append((pos, b_pos, False, False))

    # Push box
    if m_pos == b_pos and not on_box:
        for pos in ['A', 'B', 'C']:
            if pos != m_pos:
                states.append((pos, pos, False, False))

    # Climb box
    if m_pos == b_pos and not on_box:
        states.append((m_pos, b_pos, True, False))

    # Grasp banana
    if m_pos == 'C' and on_box:
        states.append((m_pos, b_pos, True, True))

    return states


def bfs(start):
    queue = deque([(start, [])])
    visited = set()

    while queue:
        state, path = queue.popleft()

        if state in visited:
            continue

        visited.add(state)

        if state[3] == True:
            return path + [state]

        for next_state in get_next_states(state):
            queue.append((next_state, path + [state]))

    return None


# Run
solution = bfs(start)

for step in solution:
    print(step)
