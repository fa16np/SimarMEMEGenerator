package me.kindeep.simarmemegenerator;

import android.content.Intent;
import android.graphics.Matrix;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;

    Bitmap simar_temp;

    Bitmap base_image_temp;  //Main Image

    final int OPEN_FILE = 1;

    //First method get opened when you run app--------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  //setting current veiw

        //Making a button----------------------------------------
        Button open = findViewById(R.id.load_button);



        //click listener for button--------------------------------
        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showToast("SELECT FILE GOD DAMNIT");

                selectImageFromGallery();

            }
        });


        //Using face detector for detecting all the faces in the photo--------
        FaceDetector detector = new FaceDetector.Builder(getApplicationContext())
                .setProminentFaceOnly(true)
                .build();



        //Image veiw for showing images---------------------------------------
        imageView = (ImageView) findViewById(R.id.imgview);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inMutable = true;

        base_image_temp = BitmapFactory.decodeResource(
                getApplicationContext().getResources(),
                R.raw.lead_720_405,
                options);

        Bitmap resultBitmap = Bitmap.createBitmap(base_image_temp.getWidth(), base_image_temp.getHeight(), Bitmap.Config.RGB_565);



        //Making canvas for image editing and then showing it later----------
        Canvas resultCanvas = new Canvas(resultBitmap);
        resultCanvas.drawBitmap(base_image_temp, 0, 0, null);


        simar_temp = BitmapFactory.decodeResource(
                getApplicationContext().getResources(),
                R.drawable.simar,
                options);


        FaceDetector faceDetector = new
                FaceDetector.Builder(this).setTrackingEnabled(false)
                .build();
        if (!faceDetector.isOperational()) {
            Toast.makeText(this, "Could not set up face detector!", Toast.LENGTH_SHORT).show();
            return;
        }


        Frame frame = new Frame.Builder().setBitmap(base_image_temp).build();

        //All the faces-----------------------------------------------------
        SparseArray<Face> faces = faceDetector.detect(frame);


        //Running for loop on faces to edit and editing it one by one--------
        for (int i = 0; i < faces.size(); i++) {
            Face thisFace = faces.valueAt(i);
            float x1 = thisFace.getPosition().x;
            float y1 = thisFace.getPosition().y;
            float x2 = x1 + thisFace.getWidth();
            float y2 = y1 + thisFace.getHeight();

            float x_left = x1 - thisFace.getWidth() / 2;
            float y_top = x1 - thisFace.getHeight() / 2;
            Bitmap temp_bitmap_simar = Bitmap.createBitmap((int) thisFace.getWidth(), (int) thisFace.getHeight(), Bitmap.Config.RGB_565);

            resultCanvas.drawBitmap(getResizedBitmap(simar_temp, (int) thisFace.getWidth(), (int) thisFace.getHeight()), x1, y1, null);
        }

        imageView.setImageDrawable(new BitmapDrawable(getResources(), resultBitmap));

    }


    /*
    * Selects image from gallery and loads it for the processing
    * */
    public void  selectImageFromGallery(){
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, OPEN_FILE);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if( requestCode == OPEN_FILE){
            Toast.makeText(this, "YES", Toast.LENGTH_LONG).show();
            Uri imageData = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(imageData);

                Bitmap temp = BitmapFactory.decodeStream(inputStream);

                setImageViewBitmap(temp);


            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(this, "Wasnt able to open Image! :(", Toast.LENGTH_LONG).show();
            }

        }
    }






    //Setting image veiw
    void setImageViewBitmap(Bitmap bitmap) {
        imageView.setImageDrawable(new BitmapDrawable(getResources(), bitmap));
    }

    //Fo bitmap image resizing
    public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);
        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
        return resizedBitmap;
    }


    //To show the text "Select file god damn it
    void showToast(Object a) {
        Toast.makeText(this, a.toString(), Toast.LENGTH_SHORT).show();
    }




    //--------------------------------------------Coming soon!
    public void FromCamera() {

//        Log.i("camera", "startCameraActivity()");
//        File file = new File(path);
//        Uri outputFileUri = Uri.fromFile(file);
//        Intent intent = new Intent(
//                android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
//        startActivityForResult(intent, 1);

    }

}
