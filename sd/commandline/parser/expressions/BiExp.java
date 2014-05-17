package parser.expressions;

/**
 * Created by KateKate on 12.05.14.
 */
abstract class BiExp implements Exp {
    public final Exp left;
    public final Exp right;

    protected BiExp(Exp left, Exp right) {
        this.left = left;
        this.right = right;
    }
}