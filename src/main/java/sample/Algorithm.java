package sample;

public enum  Algorithm {
    BruteForce("BruteForce"),Logical("Logical");

    private String algo;

    Algorithm(String algo) {

        this.algo = algo;
    }

    @Override
    public String toString() {
        return algo;
    }
}
