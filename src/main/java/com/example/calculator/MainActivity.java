package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private Button nb1,nb2,nb3,nb4,nb5,nb6,nb7,nb8,nb9,nb0,btnAC,btnDEL,btnChia,btnNhan,btnTru,btnCong,btnCham,btnBang;

    private TextView textviewHistory,textviewResult;

    private String number=null;

    double lastnumber=0, firstnumber=0;

    boolean operator=false;

    String status=null;

    String pattern = "###,###.#####";
    DecimalFormat decimalFormat=new DecimalFormat(pattern);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nb0=this.findViewById(R.id.nb0);
        nb1=this.findViewById(R.id.nb1);
        nb2=this.findViewById(R.id.nb2);
        nb3=this.findViewById(R.id.nb3);
        nb4=this.findViewById(R.id.nb4);
        nb5=this.findViewById(R.id.nb5);
        nb6=this.findViewById(R.id.nb6);
        nb7=this.findViewById(R.id.nb7);
        nb8=this.findViewById(R.id.nb8);
        nb9=this.findViewById(R.id.nb9);


        btnAC=this.findViewById(R.id.btnAC);
        btnDEL=this.findViewById(R.id.btnDEL);
        btnChia=this.findViewById(R.id.btnChia);
        btnNhan=this.findViewById(R.id.btnNhan);
        btnTru=this.findViewById(R.id.btnTru);
        btnCong=this.findViewById(R.id.btnCong);
        btnCham=this.findViewById(R.id.btnCham);
        btnBang=this.findViewById(R.id.btnBang);

        textviewHistory=this.findViewById(R.id.textviewHistory);
        textviewResult=this.findViewById(R.id.textviewResult);

        nb0.setOnClickListener(view -> clicknumber( "0" ));
        nb1.setOnClickListener(view -> clicknumber( "1" ));
        nb2.setOnClickListener(view -> clicknumber( "2" ));
        nb3.setOnClickListener(view -> clicknumber( "3" ));
        nb4.setOnClickListener(view -> clicknumber( "4" ));
        nb5.setOnClickListener(view -> clicknumber( "5" ));
        nb6.setOnClickListener(view -> clicknumber( "6" ));
        nb7.setOnClickListener(view -> clicknumber( "7" ));
        nb8.setOnClickListener(view -> clicknumber( "8" ));
        nb9.setOnClickListener(view -> clicknumber( "9" ));

        btnCong.setOnClickListener(view -> {
            if(operator){
                if(status=="multi"){
                    Nhan();
                }else {
                    if(status=="div"){
                        Chia();
                    }else {
                        if(status=="minus"){
                            Tru();
                        }else {
                                Cong();
                        }
                    }
                }
            }
            operator=false;
            number=null;
            status="sum";
        });
        btnTru.setOnClickListener(view -> {
            if(operator){
                if(status=="multi"){
                    Nhan();
                }else {
                    if(status=="div"){
                        Chia();
                    }else {
                        if(status=="sum"){
                            Cong();
                        }else {
                            Tru();
                        }
                    }
                }
            }
            operator=false;
            number=null;
            status="minus";
        });
        btnNhan.setOnClickListener(view -> {
            if(operator){
                if(status=="sum"){
                    Cong();
                }else {
                    if(status=="div"){
                        Chia();
                    }else {
                        if(status=="minus"){
                            Tru();
                        }else {
                            Nhan();
                        }
                    }
                }
            }
            operator=false;
            number=null;
            status="multi";
        });
        btnChia.setOnClickListener(view -> {
            if(operator){
                if(status=="multi"){
                    Nhan();
                }else {
                    if(status=="sum"){
                        Cong();
                    }else {
                        if(status=="minus"){
                            Tru();
                        }else {
                            Chia();
                        }
                    }
                }
            }
            operator=false;
            number=null;
            status="div";
        });

        btnBang.setOnClickListener(view -> {
            if(operator){
                if(status=="multi"){
                    Nhan();
                }else {
                    if(status=="sum"){
                        Cong();
                    }else {
                        if(status=="minus"){
                            Tru();
                        }else {
                            if(status=="div"){
                            Chia();
                        }else {
                                firstnumber=Double.parseDouble(textviewResult.getText().toString());
                            }
                        }
                    }
                }
            }
            operator=false;
            number=null;
        });

        btnAC.setOnClickListener(view -> {
            number=null;
            operator=false;
            textviewResult.setText("0");
            textviewHistory.setText("");
            firstnumber=0;
            lastnumber=0;
        });

        btnDEL.setOnClickListener(view -> {
            number=number.substring(0,number.length()-1);
            textviewResult.setText(number);
        });

        btnCham.setOnClickListener(view -> {
            if(number==null){
                number="0.";
            }else {
                number=number+".";
            }
            textviewResult.setText(number);
        });
    }

    public void clicknumber (String view){
        if(number==null){
            number=view;
        }
        else {
         number=number+view;
        }

        textviewResult.setText(number);
        operator=true;
    }

    public void Tru(){
        if(firstnumber==0){
            firstnumber=Double.parseDouble(textviewResult.getText().toString());
        }
        else {
            lastnumber=Double.parseDouble(textviewResult.getText().toString());
            firstnumber=firstnumber-lastnumber;
        }
        textviewResult.setText(decimalFormat.format(firstnumber));
    }

    public void Cong(){
            lastnumber=Double.parseDouble(textviewResult.getText().toString());
            firstnumber=firstnumber+lastnumber;
        textviewResult.setText(decimalFormat.format(firstnumber));
    }
    public void Nhan(){
        if(firstnumber==0){
            firstnumber=1;
        }
            lastnumber=Double.parseDouble(textviewResult.getText().toString());
            firstnumber=firstnumber*lastnumber;
        textviewResult.setText(decimalFormat.format(firstnumber));
    }
    public void Chia(){
        if(firstnumber==0){
            lastnumber=Double.parseDouble(textviewResult.getText().toString());
            firstnumber=lastnumber;
        }
        else {
            lastnumber=Double.parseDouble(textviewResult.getText().toString());
            firstnumber=firstnumber/lastnumber;
        }
        textviewResult.setText(decimalFormat.format(firstnumber));
    }
}