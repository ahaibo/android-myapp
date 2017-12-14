package hai.com.myapp;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DialogActivity extends AppCompatActivity {
    private EditText editText;
    private String[] multiChoiceItems;
    private boolean[] multiChoiceCheckedItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        editText = (EditText) findViewById(R.id.et_dialog_type);

        //getContentResolver().registerContentObserver(Uri.parse(""), false, new MyObserver(new Handler()));

    }

    private class MyObserver extends ContentObserver {

        /**
         * Creates a content observer.
         *
         * @param handler The handler to run {@link #onChange} on, or null if none.
         */
        public MyObserver(Handler handler) {
            super(handler);
        }

        @Override
        public void onChange(boolean selfChange, Uri uri) {
            super.onChange(selfChange, uri);
        }
    }

    public void showDialog(View view) {
        System.out.println("enter method showDialog...");

        Editable editable = editText.getText();
        String type = editable.toString();
        System.out.println("type: " + type);

        switch (type) {
            case "1":
                normalDialog();
                break;
            case "2":
                singleChoiceDialog();
                break;
            case "3":
                multiChoiceDialog();
                break;
            case "4":
                progressDialog();
                break;
            default:
                Toast.makeText(getApplicationContext(), "please enter type which: 1,2,3,4", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void normalDialog() {
        System.out.println("enter method normalDialog...");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Title: a'm is normal dialog");
        builder.setMessage("Message...");

        //buttons setting
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "OK Operation...", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Cancel Operation...", Toast.LENGTH_SHORT).show();
            }
        });

        builder.show();
    }

    private void singleChoiceDialog() {
        System.out.println("enter method singleChoiceDialog...");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Title: a'm is singleChoice dialog");

        final String[] items = {"jack", "tf-boys", "bingbing", "hai", "baoqiang"};

        builder.setSingleChoiceItems(items, 4, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), items[which], Toast.LENGTH_SHORT).show();
                //让对话框消失
                dialog.dismiss();
            }
        });

        builder.show();
    }

    private void multiChoiceDialog() {
        System.out.println("enter method multiChoiceDialog...");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Title: a'm is multiChoice dialog");

        multiChoiceItems = new String[]{"watermelon", "mango", "apple", "durain", "lichee"};
        multiChoiceCheckedItems = new boolean[]{false, true, false, false, true};

        builder.setMultiChoiceItems(multiChoiceItems, multiChoiceCheckedItems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                Toast.makeText(getApplicationContext(), multiChoiceItems[which] + (isChecked ? "被选中" : "没选中"), Toast.LENGTH_SHORT).show();
//                dialog.dismiss();
                multiChoiceCheckedItems[which] = isChecked;
            }
        });

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                System.out.println("which: " + which);
                System.out.println("choice items:");
                for (int i = 0, len = multiChoiceItems.length; i < len; i++) {
                    if (multiChoiceCheckedItems[i]) {
                        System.out.println(multiChoiceItems[i]);
                    }
                }
                dialog.dismiss();
            }
        });

        builder.show();
    }

    private void progressDialog() {
        System.out.println("enter method progressDialog...");
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setTitle("Title: a'm is progressDialog dialog。正在玩命下载...");
        dialog.setMax(100);
        dialog.show();

        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i <= 100; i++) {
                    dialog.setProgress(i);
                    SystemClock.sleep(100);
                }
                SystemClock.sleep(1500);
                dialog.dismiss();
            }
        }.start();
    }

}
