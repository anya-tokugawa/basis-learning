// Hello.java
import java.io.*;
////////////////////////////////////////////////
// クラスプログラミングのためのサンプルクラス

// HelloPrintクラス

class HelloPrint {
    // <----- ここにコンストラクタ（後述）が隠れている。------>
    void println(){
        System.out.println("Hello, World!");
    }
    void println(String name){
        System.out.println("Hello, " + name);
    }
}

// Personクラス

class Person{
    // インデントは、非常に大事だが、
    // 書き方によっては、下記のようにワザとずらして可読性を高めているものもある。
    // ただし、Pythonなどのインデントで構造を制御している場合は不可。
    // 大学の課題などで、このような書き方をした場合、担当教員によっては注意を受けるかも。
    static int    sumPerson = 0; // <==== STATICなので、インスタンス同士で値が共有される。
           int    numPerson;
           int    type;
           String name;
    /* クラス名と同じ名前のメソッドのようなものを「コンストラクタ」という。
     * コンストラクタの特徴として
     * - 返り値の指定などがないこと
     * - インスタンス変数への代入
     * などが見られる。
     * コンストラクタは、インスタンスを作成する際（例: new String()みたいな際）に呼び出される。
     * 上記HelloPrintクラスでは、コンストラクタを明記していない。この場合は、デフォルトコンストラクタが呼び出される。
     * デフォルトコンストラクタは、別のクラスからの継承（別章で説明します）などがない限り、何も処理が書いていない。
     */
    Person(){
        this.numPerson = ++sumPerson; //インクリメントと同時に変数に代入する。前置記法はこんな感じに使う。
        this.type      = 1;
        this.name      = "no name";
        System.out.println("Born a Person - " + this.name); // DEBUG用
    }
    // コンストラクタもオーバーロードできる。
    Person(String name){
        this.numPerson = ++sumPerson;
        this.type      = 2;
        this.name      = name;
        System.out.println("Born a Person - " + this.name); // DEBUG用
    }
    /* 下記をSetter（セッター）やGetter（ゲッター）という。
     * これらは、値をセット（代入）したりゲット（取得）したりするためだけのメソッドである。
     * 例えば、名簿を管理する「Member」というクラスがあったときに、名簿情報に関する変数を
     * 直接他のクラスから触る、つまり値の処理の記述を他のクラスに書くのは、リスクがある（生のデータをそのまま弄る）
     * SetterやGetterを使って、「Member」クラス内のメソッドに代入や取得を委任することによって、安全性が増す。
     * また、名簿に関する代入や取得の「機能」をMember...名簿クラスに集約することによって、設計もシンプルになる。
     * これは、APIなどの概念に近い。
     */
    void setName(String name){
        this.name = name; 
        // thisは自分自身という意味です。ここではクラスインスタンスを示します。
        // この場合、
        // 左辺がクラスインスタンスに紐付けられたインスタンス変数のnameで、
        // 右辺が引数で定義されたローカル変数のnameになります。
    }
    // 言語によっては、こういう書き方もある。
    String getName(){   return name;}
    int getType(){      return this.type;}
    int getNumPerson(){ return this.numPerson;}
    //////////////////////////////////////////////////////
    // STATIC変数の場合は、メソッドをインスタンス化する必要がないので、STATICメソッドで良い。
    static int getSumPerson(){ 
        return sumPerson; 
        /* sumPersonはSTATIC変数です。インスタンス変数ではないので、thisをつけることはできません。*/
    }
    ///////////////////////////////////////////////////////////////////////////////////
    // 注意しなければいけないこととして、getやsetは単に代入や取得をするものであるべきということ。
    // 例えば、メソッドがgetNameで、this.nameが`John Smith`なのに、`John_Smith`が返ってきてはいけない。
    // この場合は、`getEscapedBrankName`など、それに合ったメソッド名を定義するべきである。

    // SetterやGetterと似たようなものとして、is~などがある。is~はXXはYYである。というbooleanを返す。
    boolean isMatchedName(String name){
        return name.equals(this.name);
    }
}
// 以下がメインの処理
public class Hello{
    public static void main(String[] args) throws IOException{
        BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("==========================================");
        // HelloPrintクラスのインスタンスを生成する。
        System.out.println("Code 01: HelloWorld");
        HelloPrint hp = new HelloPrint(); // インスタンス生成
        hp.println();
        hp.println("Wayne!");
        hp.println("Lain!");
        System.out.println("==========================================");
        System.out.println("Code 02: Make a Person.");
        while(true){
            System.out.print("plz new name(or quit): ");
            String input = buf.readLine();
            if(input.equals("quit")) break;
            Person man;
            if(input.equals("")){
                man = new Person(); // Person（引数なし）インスタンス生成
            }else{
                man = new Person(input); // Person（引数あり）インスタンス生成
            }
            System.out.println("======= GET INFORMATION =======");
            System.out.println("man type: "+ man.getType()); //メソッドを利用してみる。
            System.out.println("man name: "+ man.getName());
            System.out.println("you are "  + man.getNumPerson() + " person.");
            // STATICメソッドへのアクセスは、インスタンスを介さず、クラス名で直接アクセスすることを推奨。
            System.out.println("SUM: "          + Person.getSumPerson());
            System.out.println("SUM(FromIns): " + man.getSumPerson()); // <--- つまりコレ非推奨。
            System.out.println("===============================");
        }
    }
}