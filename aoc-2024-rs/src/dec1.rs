use std::{collections::HashMap, io::stdin, iter::zip};

fn part1() {
    let reader = stdin().lines();

    let mut left_col: Vec<i32> = Vec::new();
    let mut right_col: Vec<i32> = Vec::new();

    for line in reader {
        let line = line.unwrap();
        let mut split = line.trim().split(" ");

        let left_ele = split.next().unwrap().parse::<i32>().unwrap();
        left_col.push(left_ele);

        let right_ele = split.last().unwrap().parse::<i32>().unwrap();
        right_col.push(right_ele);
    }

    left_col.sort();
    right_col.sort();

    let total_dist: i32 = zip(left_col, right_col).map(|(a, b)| (a - b).abs()).sum();
    println!("{}", total_dist);
}

fn part2() {
    let reader = stdin().lines();

    let mut left_col: Vec<i32> = Vec::new();
    let mut right_freq: HashMap<i32, i32> = HashMap::new();

    for line in reader {
        let line = line.unwrap();
        let mut split = line.trim().split(" ");

        let left_ele = split.next().unwrap().parse::<i32>().unwrap();
        left_col.push(left_ele);

        let right_ele = split.last().unwrap().parse::<i32>().unwrap();
        if right_freq.contains_key(&right_ele) {
            right_freq.insert(right_ele, right_freq.get(&right_ele).unwrap() + 1);
        } else {
            right_freq.insert(right_ele, 1);
        }
    }

    let mut similarity_score = 0;
    for ele in left_col {
        if right_freq.contains_key(&ele) {
            similarity_score += ele * right_freq.get(&ele).unwrap();
        }
    }
    println!("{}", similarity_score);
}

pub fn main() {
    part2();
}
