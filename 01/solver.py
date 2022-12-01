input_file = open('inputs.txt', 'r')
lines = input_file.readlines()

def get_max_calories(lines):
    max_calories = 0
    current = 0

    for line in lines:
        if line == "\n":
            if current > max_calories:
                max_calories = current
            current = 0
        else:
            current += int(line)
    return max_calories

def move_list(l, index, value, n):
    if n == index + 1:
        l[index] = value
    else:
        old = l[index]
        l[index] = value
        move_list(l, index + 1, old, n)

def get_max_n_calories(lines, n):
    max_calories = [0] * n
    current = 0
    for line in lines:
        if line == "\n":
            for i in range(n):
                if current > max_calories[i]:
                    move_list(max_calories, i, current, n)
                    break
            current = 0
        else:
            current += int(line)
    return max_calories


print(f"Max calories carried by elf is {get_max_calories(lines)}")
print(f"Sum of 3 max calories carried by elfs is {sum(get_max_n_calories(lines, 3))}")
