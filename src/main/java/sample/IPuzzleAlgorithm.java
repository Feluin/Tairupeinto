package sample;

public interface IPuzzleAlgorithm {

     void step() throws NotSolvablePuzzleException;

     void solve() throws NotSolvablePuzzleException;
}
