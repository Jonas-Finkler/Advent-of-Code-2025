package aoc.dec07;

public abstract class TachyonManifoldElement {
    public abstract char toChar();

    public static TachyonManifoldElement fromChar(char c) {
        return switch (c) {
            case 'S' -> new Source();
//            case '|' -> new Beam();
            case '^' -> new Splitter();
            case '.' -> new Empty();
            default -> throw new IllegalArgumentException("Unknown element symbol: " + c);
        };
    }

    public abstract static class BeamLike extends TachyonManifoldElement {
        protected long nTimelines;

        public Beam propagate(TachyonManifoldElement dest) {
            return switch (dest) {
                case Empty empty -> new Beam(nTimelines);
                case Beam beam -> new Beam(nTimelines + beam.nTimelines);
                default -> throw new IllegalArgumentException("Cannot propagate beam to element: " + dest.toChar());
            };
        }
        public long getNTimelines() {
            return nTimelines;
        }
    }

    public static class Source extends BeamLike {
        @Override
        public char toChar() {
            return 'S';
        }

        public Source() {
            this.nTimelines = 1;
        }
    }

    public static class Beam extends BeamLike {
        @Override
        public char toChar() {
            return '|';
        }

        public Beam(long nTimelines) {
            this.nTimelines = nTimelines;
        }
    }

    public static class Splitter extends TachyonManifoldElement {
        @Override
        public char toChar() {
            return '^';
        }
    }

    public static class Empty extends TachyonManifoldElement {
        @Override
        public char toChar() {
            return '.';
        }
    }

}
