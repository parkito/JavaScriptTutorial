function recursion(k) {
    if (k > 1)
        recursion(k - 1);
    return k.toString() + " ".toString();
}