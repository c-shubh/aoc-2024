debug = not True


def get_input():
    while True:
        try:
            line = input()
        except EOFError:
            break
        yield line


def bounds_check(grid: list[str], i: int, j: int) -> bool:
    return 0 <= i < len(grid) and 0 <= j < len(grid[i])


def part1():
    key = "XMAS"

    key = "XMAS"
    directions = [
        (-1, 0),  # up
        (1, 0),  # down
        (0, -1),  # left
        (0, 1),  # right
        (-1, -1),  # up-left
        (-1, 1),  # up-right
        (1, -1),  # down-left
        (1, 1),  # down-right
    ]

    def check_direction(grid: list[str], i: int, j: int, di: int, dj: int) -> bool:
        for k in range(len(key)):
            ni, nj = i + k * di, j + k * dj
            if not (bounds_check(grid, ni, nj) and grid[ni][nj] == key[k]):
                return False
        return True

    grid: list[str] = []
    for line in get_input():
        grid.append(line)
    if debug:
        print(grid)

    count = 0
    for i in range(len(grid)):
        for j in range(len(grid[i])):
            for di, dj in directions:
                if check_direction(grid, i, j, di, dj):
                    count += 1
    print(count)


def part2():
    def check(grid: list[str], i: int, j: int) -> bool:
        if not (
            bounds_check(grid, i - 1, j - 1)
            and bounds_check(grid, i + 1, j + 1)
            and bounds_check(grid, i + 1, j - 1)
            and bounds_check(grid, i - 1, j + 1)
        ):
            return False
        if (
            (grid[i - 1][j - 1] == "M" and grid[i + 1][j + 1] == "S")
            or (grid[i - 1][j - 1] == "S" and grid[i + 1][j + 1] == "M")
        ) and (
            (grid[i + 1][j - 1] == "M" and grid[i - 1][j + 1] == "S")
            or (grid[i + 1][j - 1] == "S" and grid[i - 1][j + 1] == "M")
        ):
            return True
        return False

    grid: list[str] = []
    for line in get_input():
        grid.append(line)
    count = 0
    for i in range(len(grid)):
        for j in range(len(grid[i])):
            if grid[i][j] == "A" and check(grid, i, j):
                count += 1
    print(count)


if __name__ == "__main__":
    part2()
