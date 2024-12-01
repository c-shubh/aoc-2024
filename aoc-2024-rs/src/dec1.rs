use std::{io::stdin, iter::zip};

pub fn main() {
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
