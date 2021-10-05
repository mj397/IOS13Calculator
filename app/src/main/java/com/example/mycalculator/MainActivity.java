package com.example.mycalculator;

import android.content.Context;
import android.os.Bundle;
import android.text.ClipboardManager;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import java.text.DecimalFormat;
import java.util.ArrayList;


/**
 * Created by Mohamad Jamous on 9/12/2021
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Context mContext;
    private TextView mTvTotal, mTvCalculations;
    private Button btnClear, btnPerLeft, btnPerRight,
    btnDivided, btnSeven, btnEight, btnNine, btnTimes, btnFour, btnFive, btnSix,
    btnMinus, btnOne, btnTwo, btnThree, btnPlus, btnZero, btnPoint, btnEqual;
    private ArrayList<String> mUserEntries = new ArrayList<>();
    private ArrayList<String> mSymbolsList = new ArrayList<>();
    private boolean isEqualPressed = false;
    private boolean isPointPressed = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //initializing views
        intiValues();


        RelativeLayout lin = (RelativeLayout) findViewById(R.id.relative_layout);
        //registering mTvTotal textView for getting the total value to clipboard
        registerForContextMenu(mTvTotal);
        lin.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this) {
            public void onSwipeTop()
            {
            }
            public void onSwipeRight()
            {
                if (!(mUserEntries.isEmpty())) {

                    boolean isEqual = false;
                    for (int i = 0; i < mSymbolsList.size(); i++) {
                        if (mUserEntries.get(mUserEntries.size() - 1).equals(mSymbolsList.get(i))) {
                            isEqual = true;
                            break;
                        }
                    }
                    if (mUserEntries.size() == 1)
                    {
                        mUserEntries.clear();
                        mTvTotal.setText("");
                        mTvCalculations.setText("");
                    }
                    else
                    {
                        mUserEntries.remove(mUserEntries.size() - 1);
                        displayText(false);
                    }

                    if (!isEqual && isEqualPressed)
                    {
                        getTotal(true);
                        displayText(true);
                    }


                }

            }
            public void onSwipeLeft()
            {

                if (!(mUserEntries.isEmpty()))
                {
                    boolean isEqual = false;
                    for (int i = 0; i < mSymbolsList.size(); i++) {
                        if (mUserEntries.get(mUserEntries.size() - 1).equals(mSymbolsList.get(i))) {
                            isEqual = true;
                            break;
                        }
                    }

                    if (mUserEntries.size() == 1)
                    {
                        mUserEntries.clear();
                        mTvTotal.setText("");
                        mTvCalculations.setText("");
                    }
                    else
                    {
                        mUserEntries.remove(mUserEntries.size() - 1);
                        displayText(false);
                    }

                    if (!isEqual && isEqualPressed) {
                        getTotal(true);
                        displayText(true);
                    }
                }

            }
            public void onSwipeBottom()
            {

            }
        });


    }

    private void intiValues()
    {
        mContext = this;
        mTvTotal = (TextView) findViewById(R.id.tv_total);
        mTvCalculations = (TextView) findViewById(R.id.tv_calculations);


        btnClear = (Button) findViewById(R.id.button_clear);
        btnPerLeft = (Button) findViewById(R.id.button_per_left);
        btnPerRight = (Button) findViewById(R.id.button_per_right);
        btnDivided = (Button) findViewById(R.id.button_divid);
        btnSeven = (Button) findViewById(R.id.button_7);
        btnEight = (Button) findViewById(R.id.button_8);
        btnNine = (Button) findViewById(R.id.button_9);
        btnTimes = (Button) findViewById(R.id.button_times);
        btnFour = (Button) findViewById(R.id.button_4);
        btnFive = (Button) findViewById(R.id.button_5);
        btnSix = (Button) findViewById(R.id.button_6);
        btnMinus = (Button) findViewById(R.id.button_minus);
        btnOne = (Button) findViewById(R.id.button_1);
        btnTwo = (Button) findViewById(R.id.button_2);
        btnThree = (Button) findViewById(R.id.button_3);
        btnPlus = (Button) findViewById(R.id.button_plus);
        btnZero = (Button) findViewById(R.id.button_0);
        btnPoint = (Button) findViewById(R.id.button_point);
        btnEqual = (Button) findViewById(R.id.button_equal);

        btnClear.setOnClickListener(this);
        btnPerLeft.setOnClickListener(this);
        btnPerRight.setOnClickListener(this);
        btnDivided.setOnClickListener(this);
        btnSeven.setOnClickListener(this);
        btnEight.setOnClickListener(this);
        btnNine.setOnClickListener(this);
        btnTimes.setOnClickListener(this);
        btnFour.setOnClickListener(this);
        btnFive.setOnClickListener(this);
        btnSix.setOnClickListener(this);
        btnMinus.setOnClickListener(this);
        btnOne.setOnClickListener(this);
        btnTwo.setOnClickListener(this);
        btnThree.setOnClickListener(this);
        btnPlus.setOnClickListener(this);
        btnZero.setOnClickListener(this);
        btnPoint.setOnClickListener(this);
        btnEqual.setOnClickListener(this);


        mSymbolsList.add("/");
        mSymbolsList.add("×");
        mSymbolsList.add("−");
        mSymbolsList.add("+");

    }



    @Override
    public void onClick(View view)
    {
        int viewId = view.getId();
        System.out.println("viewId: " + viewId);


        switch (view.getId())
        {
            case R.id.button_clear:
                mUserEntries.clear();
                mTvCalculations.setText("");
                mTvTotal.setText("");
                isPointPressed = false;
                break;

            case R.id.button_per_left:
                if (checkForInput())
                {
                    mUserEntries.add("(");
                    displayText(false);
                }

                break;

            case R.id.button_per_right:
                if (checkForInput()) {
                    mUserEntries.add(")");
                    displayText(false);
                }
                break;

            case R.id.button_divid:
                if (checkForInput()) {
                    AnimateViews('s');
                    addSymbol("/");
                    isPointPressed = false;
                }
                break;

            case R.id.button_7:
                if (checkForInput()) {
                    AnimateViews('t');
                    mUserEntries.add("7");
                    displayText(false);
                }
                break;
            case R.id.button_8:
                if (checkForInput()) {
                    AnimateViews('t');
                    mUserEntries.add("8");
                    displayText(false);
                }
                break;
            case R.id.button_9:
                if (checkForInput()) {
                    AnimateViews('t');
                    mUserEntries.add("9");
                    displayText(false);
                }
                break;
            case R.id.button_times:
                if (checkForInput()) {
                    AnimateViews('s');
                    addSymbol("×");
                    isPointPressed = false;
                }
                break;
            case R.id.button_4:
                if (checkForInput()) {
                    AnimateViews('t');
                    mUserEntries.add("4");
                    displayText(false);
                }
                break;
            case R.id.button_5:
                if (checkForInput()) {
                    AnimateViews('t');
                    mUserEntries.add("5");
                    displayText(false);
                }
                break;
            case R.id.button_6:
                if (checkForInput()) {
                    AnimateViews('t');
                    mUserEntries.add("6");
                    displayText(false);
                }
                break;
            case R.id.button_minus:
                if (checkForInput()) {
                    AnimateViews('s');
                    addSymbol("−");
                    isPointPressed = false;
                }
                break;
            case R.id.button_1:
                if (checkForInput()) {
                    AnimateViews('t');
                    mUserEntries.add("1");
                    displayText(false);
                }
                break;
            case R.id.button_2:
                if (checkForInput()) {
                    AnimateViews('t');
                    mUserEntries.add("2");
                    displayText(false);
                }
                break;
            case R.id.button_3:
                if (checkForInput()) {
                    AnimateViews('t');
                    mUserEntries.add("3");
                    displayText(false);
                }
                break;
            case R.id.button_plus:
                if (checkForInput()) {
                    AnimateViews('s');
                    addSymbol("+");
                    isPointPressed = false;
                }
                break;
            case R.id.button_0:
                if (checkForInput()) {
                    AnimateViews('t');
                    mUserEntries.add("0");
                    displayText(false);
                }
                break;
            case R.id.button_point:
                if (!isPointPressed)
                {
                    AnimateViews('s');
                    addSymbol(".");
                    isPointPressed = true;
                }
                else
                {
                    Toast.makeText(mContext, getString(R.string.invalid_input), Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.button_equal:
                isEqualPressed = true;
                getTotal(false);
                displayText(true);
                break;

        }

    }


    private void displayText(boolean isEqualPressed)
    {

        if (!(mUserEntries.isEmpty()))
        {
            int size = mUserEntries.size();
            String content = "";
            String visualContent = "";

            for (int i = 0; i < size; i++)
            {
                System.out.println("contentBefore: " + content);
                visualContent += mUserEntries.get(i);
                if (mUserEntries.get(i).equals("×"))
                {
                    content +=  "*";
                }
                else if (mUserEntries.get(i).equals("−"))
                {
                    content +=  "-";
                }
                else if (mUserEntries.get(i).equals("× e"))
                {
                    content +=  "e";
                }
                else
                {
                    content += mUserEntries.get(i);
                }

                System.out.println("contentAfter: " + content);

            }

            StringBuilder result = new StringBuilder();
            SpannableStringBuilder builder = new SpannableStringBuilder();


            for (int i= 0 ,n = visualContent.length(); i < n; i++)
            {
                char c = visualContent.charAt(i);
                System.out.println("cBefore: " + c);

                if (!(c == ')' || c == '(' ))
                {
                    System.out.println("cReplaced: " + c);
                    result.append(" ");
                }

                if (c == '/' || c == '+' || c == '×' || c == '−')
                {
                    String string1 = String.valueOf(" " + c + " ");
                    SpannableString redSpannable1= new SpannableString(string1);
                    redSpannable1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.edge_buttons_bg)), 0, string1.length(), 0);
                    builder.append(redSpannable1);
                }
                else
                {

                    String string = String.valueOf(c);
                    SpannableString redSpannable= new SpannableString(string);
                    if (isEqualPressed)
                    {
                        redSpannable.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.grey)), 0, string.length(), 0);
                    }
                    else
                    {
                        redSpannable.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.black)), 0, string.length(), 0);
                    }
                    builder.append(redSpannable);

                }

                result.append(visualContent.charAt(i));

                System.out.println("visualContentAfter: " + visualContent);
            }

            System.out.println("contentValue: " + content);


            mTvCalculations.setText(builder, TextView.BufferType.SPANNABLE);
        }


    }



    private void addSymbol(String symbol)
    {
        if (!(mUserEntries.isEmpty()))
        {
            int size = mSymbolsList.size();

            String lastElement = mUserEntries.get(mUserEntries.size() - 1);

            for (int i = 0; i < size; i++)
            {
                if (lastElement.equals(mSymbolsList.get(i)))
                {
                    System.out.println("removedValue: " + mUserEntries.get(mUserEntries.size() - 1));
                    mUserEntries.remove(mUserEntries.size() - 1);
                    break;
                }
            }
                mUserEntries.add(symbol);
                displayText(false);
        }

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        //user has long pressed your TextView
        menu.add(0, v.getId(), 0, "copy");

        //cast the received View to TextView so that you can get its text
        TextView yourTextView = (TextView) v;

        //place your TextView's text in clipboard
        ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        clipboard.setText(yourTextView.getText());
        System.out.println("yourTextView.getText(): " + yourTextView.getText());

    }

    private void AnimateViews(char state)
    {

        if (state == 'e')
        {
            mTvCalculations.setTextSize(19);
            mTvCalculations.setTextColor(getResources().getColor(R.color.grey));
            mTvCalculations.setGravity(Gravity.TOP);

            TranslateAnimation animate = new TranslateAnimation(
                    0,
                    0,
                    0,
                    -60);
            animate.setDuration(200);
            animate.setFillAfter(true);

            mTvCalculations.startAnimation(animate);
        }
        else if (state == 't' && isEqualPressed)
        {
            mTvCalculations.setTextSize(40);
            mTvCalculations.setTextColor(getResources().getColor(R.color.black));
            mUserEntries.clear();

            mTvCalculations.setText("");
            mTvTotal.setText("");
            mTvCalculations.setGravity(Gravity.CENTER_VERTICAL);
            isEqualPressed = false;
        }
        else if (state == 's' && !(mTvTotal.getText().equals("")))
        {

            mTvCalculations.setTextSize(39);
            mTvCalculations.setTextColor(R.attr.textColor);
            mUserEntries.clear();
            mUserEntries.add(mTvTotal.getText().toString());
            mTvTotal.setText("");
            isEqualPressed = false;
        }

    }


    private boolean checkForInput()
    {
        String content = "";
        String[] mNumbers;
        ArrayList<String> mSymbols = new ArrayList<>();

        if (!(mUserEntries.isEmpty()))
        {
            for (int i = 0, n = mUserEntries.size(); i < n; i++)
            {
                content += mUserEntries.get(i);
            }



            int count = 0;
            for (int i = 0, len = content.length(); i < len; i++) {
                if (Character.isDigit(content.charAt(i)))
                {
                    count++;
                }
            }

            if (count == 25)
            {
                Toast.makeText(mContext, getString(R.string.digit_error), Toast.LENGTH_SHORT).show();
                return false;
            }

        }
        return true;
    }

    private void getTotal(boolean isSwiping) {

        if (!(mUserEntries.isEmpty()))
        {
            int size = mUserEntries.size();
            String content = "";

            for (int i = 0; i < size; i++) {
                System.out.println("contentBefore: " + content);
                if (mUserEntries.get(i).equals("×")) {
                    content += "*";
                } else if (mUserEntries.get(i).equals("−")) {
                    content += "-";
                } else if (mUserEntries.get(i).equals("× e")) {
                    content += "e";
                } else {
                    content += mUserEntries.get(i);
                }

            }

            System.out.println("contentAfter: " + content);

            boolean isError = false;
            for (int i = 0; i < mSymbolsList.size(); i++) {
                if (mUserEntries.get(mUserEntries.size() - 1).equals(mSymbolsList.get(i))) {
                    isError = true;
                    break;
                }
            }
            if (isError && !isSwiping)
            {
                Toast.makeText(mContext, getString(R.string.invalid_input), Toast.LENGTH_SHORT).show();
            }
            else if ((mUserEntries.contains("(") && !(mUserEntries.contains(")"))) || (mUserEntries.contains(")") && !(mUserEntries.contains("("))))
            {
                if (!isSwiping)
                {
                    Toast.makeText(mContext, getString(R.string.invalid_input), Toast.LENGTH_SHORT).show();
                }

            }
            else
            {
                AnimateViews('e');
                System.out.println("contentValue: " + content);
                if (content.contains(","))
                {
                    content = content.replace(",", "");
                }
                try {
                    Expression expression = new ExpressionBuilder(content).build();
                    double finalResult = expression.evaluate();
                    if ((finalResult - (int) finalResult) != 0) {

                        DecimalFormat formatTrim = new DecimalFormat("0.###");
                        String formatted = formatTrim.format(finalResult);
                        mTvTotal.setText(formatted);
                    }
                    // Integer case
                    else {
                        int resultInt = (int) finalResult;
                        System.out.println("finalResultValue: " + resultInt);
                        DecimalFormat formatter = new DecimalFormat("#,###");
                        String formatted = formatter.format(resultInt);
                        mTvTotal.setText(formatted);
                    }


                } catch (ArithmeticException ex) {
                    mTvTotal.setText("Error");
                }

            }
        }

    }

}