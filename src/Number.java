public class Number {

    int number;

    Number(int number){
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public boolean equals(Object o){
        if(o == this)
            return true;
        if(!(o instanceof Number))
            return false;
        return ((Number) o).getNumber() == this.getNumber();
    }
}
