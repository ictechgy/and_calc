package com.example.user.myapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText num1 = findViewById(R.id.num1);
        final EditText num2 = findViewById(R.id.num2);

        //final int su1 = Integer.parseInt(num1.getText().toString());
        //final int su2 = Integer.parseInt(num2.getText().toString());
        //num1과 num2라는 id값을 가지고 해당 EditText값을 통째로 num1과 num2라는 변수에 넣어두었다.
        //해당 EditText 태그에 있는 Text값을 얻어온 다음 String변환 후 정수로 변환해서 변수에 재할당시켰다.

        final Context ctx = MainActivity.this;
        //Context - 설정값. 메모리상의 MainActivity 위치를 ctx에 대입
        //이후에 Toast에서 메시지를 띄울 지점을 지정할 때 사용한다.



        //모든 메소드에 비슷한 코드가 반복되어버린다. 계산에 대한 것을 클래스로 분류해서 해도 되지만 이 역시 좋지 않은 방법이다.
        //따라서 InnerClass를 만든다. 이 계산기 내부에서만 쓸 것이므로 이 안에 만든 것이다.
        class Calc{
            private int num1, num2, res;  //인스턴스 변수. private를 안해도 되지만 은닉성을 위해 private선언
            String op;
            public void exec(){
                switch(op){
                    case "+": res = num1 + num2; break;
                    case "-": res = num1 - num2; break;
                    case "*": res = num1 * num2; break;
                    case "/": res = num1 / num2; break;
                }
            }

            public void setNum1(int num1){this.num1 = num1;}
            public int getNum1(){return this.num1;}
            public void setNum2(int num2){this.num2 = num2;}
            public int getNum2(){return this.num2;}
            public void setOp(String op){this.op = op;}
            public String getOp(){return op;}
            public void setRes(int res) {this.res = res;}
            public int getRes(){return res;}
        }  //Class Calc End

        final Calc calc = new Calc();




        findViewById(R.id.plusBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //띄울 위치, 띄울메시지, 띄울시간

                int su1 = Integer.parseInt(num1.getText().toString());
                int su2 = Integer.parseInt(num2.getText().toString());
                int result = su1 + su2;

                /*
                su1과 su2 라는 변수 및 result변수는 바깥에 두고, result의 값을 이 안에서 변경되도록 했다가 위와같이 변경하였다.
                su1과 su2라는 값을 얻어오는 것을 바깥에 두어서는 안된다. 입력하지도 않았는데 값을 얻어오려
                하기 때문에 버튼을 눌렀을 때 그 값을 받아오도록 함수 내에 작성하였다.
                다만 함수형프로그래밍원칙에 따라 메소드 내에서 외부의 값을 변경하지 않도록 해야한다.
                또한 계산하는데에 있어 외부의 값이 변경되어서는 안되기 때문에 final을 붙인 것이다.

                그렇다면 result는 외부에서 만들면 안되는가? 이 또한 안된다. 외부에서 선언했다면
                내부 클래스 함수에서 외부의 값을 변경하려 하고 있으므로.

                함수형 프로그래밍에서 순수함수라고 볼 수 있는걸까 - 그렇다. 외부에서 final로 되어있는 것은 이 메소드 안에서 값변경이 불가능하고,
                순수함수로서 구성했다고 보면 된다.
                */
                Toast.makeText(ctx, "덧셈 결과 : " + result, Toast.LENGTH_LONG).show();


                //Calc Inner클래스를 이용하여 위의 코드를 아래와 같이 추가(수정)할 수 있다.
                calc.setNum1(su1);  //역시나 안에서 밖의 변수를 건드리려 하므로 외부에서 final선언 필요
                Log.d("입력값 1", su1+"");  //로그-디버깅을 할 때는 Log클래스의 dEBUG 메소드를 사용한다.
                calc.setNum2(su2);
                Log.d("입력값 2", su2+"");
                calc.setOp("+");
                calc.exec();
                result = calc.getRes();
                Log.d("결과값", result+"");

                //로그값은 하단의 Logcat에서 볼 수 있다.
            }
        });
        findViewById(R.id.minusBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int su1 = Integer.parseInt(num1.getText().toString());
                int su2 = Integer.parseInt(num2.getText().toString());
                int result = su1 - su2;

                Toast.makeText(ctx, "뺄셈 결과 : " + result, Toast.LENGTH_LONG).show();

                calc.setNum1(su1);
                calc.setNum2(su2);
                calc.setOp("-");
                calc.exec();
                result = calc.getRes();
            }
        });

        findViewById(R.id.multiBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int su1 = Integer.parseInt(num1.getText().toString());
                int su2 = Integer.parseInt(num2.getText().toString());
                int result = su1*su2;

                Toast.makeText(ctx, "곱셈 결과 : "+ result, Toast.LENGTH_LONG).show();

                calc.setNum1(su1);
                calc.setNum2(su2);
                calc.setOp("*");
                calc.exec();
                result = calc.getRes();
            }
        });

        findViewById(R.id.divBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int su1 = Integer.parseInt(num1.getText().toString());
                int su2 = Integer.parseInt(num2.getText().toString());
                int result = su1/su2;

                Toast.makeText(ctx, "나눗셈 결과 : "+result, Toast.LENGTH_LONG).show();

                calc.setNum1(su1);
                calc.setNum2(su2);
                calc.setOp("/");
                calc.exec();
                result = calc.getRes();
            }
        });



        final TextView calcResult = findViewById(R.id.result);
        //결국 내부 메소드에 들어가는 것들은 final로서 메소드 내에서는 값변경이 안되도록 만들어야 하는 것임

        //만약 내부 메소드를 통해 특정 값이 변경되도록 해야한다면 그 값은 클래스 객체의 멤버변수임과 동시에 setter를 통해서만 변경되도록 해야하는 것 같다.

        findViewById(R.id.equalBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ctx, "결과값 : " + calc.getRes(), Toast.LENGTH_LONG).show();
                //계산은 operator를 눌렀을 때 결과값이 calc의 res변수에 담기고 이 버튼을 누르면 결과값이 나오게 된다.

                calcResult.setText("결과 : " + calc.getRes());
            }
        });



    }
}
