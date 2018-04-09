package com.example.dod.applicationnaso;

import android.app.ActionBar;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class AddPatient extends Activity {


    ImageView ivImage;
    Integer REQUEST_CAMERA=1, SELECT_FILE=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);

        ivImage =  (ImageView) findViewById(R.id.ivImage);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectImage();
            }
        });


    }



    private void SelectImage(){

        final CharSequence[] items = {"Camera", "Gallery", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(AddPatient.this);
        builder.setTitle("Add Image");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {

                if (items[i].equals("Camera")){

                    Intent intentpic = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intentpic,REQUEST_CAMERA);

                }else if(items[i].equals("Gallery")){

                    Intent intentgall = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intentgall.setType("image/*");
                    startActivityForResult(intentgall.createChooser(intentgall, "Select File"), SELECT_FILE);


                }
                else if(items[i].equals("Cancel")){

                    dialog.dismiss();


                }



            }
        });

        builder.show();

     }

     @Override

    public void onActivityResult(int requestCode, int resultCode, Intent data){

       super.onActivityResult(requestCode,resultCode,data);


         if (resultCode == Activity.RESULT_OK){

             if (requestCode == REQUEST_CAMERA){

                 Bundle bundle = data.getExtras();
                 final Bitmap bmp = (Bitmap) bundle.get("data");
                 ivImage.setImageBitmap(bmp);


             }else if (requestCode== SELECT_FILE){

                 Uri selectedImageUri = data.getData();
                 ivImage.setImageURI(selectedImageUri);



             }
         }

     }


}
