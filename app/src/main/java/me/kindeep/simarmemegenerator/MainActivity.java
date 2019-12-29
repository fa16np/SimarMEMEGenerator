package me.kindeep.simarmemegenerator;

import android.content.Intent;
import android.graphics.Matrix;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

<<<<<<< HEAD
    Bitmap simar_temp;

    Bitmap base_image_temp;  //Main Image

    FaceDetector faceDetector;

=======
>>>>>>> 142e029a5509001359ca019f3e96d2cdc4fc7f98
    final int OPEN_FILE = 1;
    private final double PATKA_RATIO = 0.2;

    BitmapFactory.Options options;

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

        imageView = (ImageView) findViewById(R.id.imgview);

<<<<<<< HEAD
        //Image veiw for showing images---------------------------------------
        imageView = (ImageView) findViewById(R.id.imgview);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inMutable = true;

        //The base image for showing when app opens
        base_image_temp = BitmapFactory.decodeResource(
                getApplicationContext().getResources(),
                R.raw.lead_720_405,
                options);


        //The resulting bitmap after face swapping
        Bitmap resultBitmap = Bitmap.createBitmap(base_image_temp.getWidth(), base_image_temp.getHeight(), Bitmap.Config.RGB_565);
=======
        options = new BitmapFactory.Options();
        options.inMutable = true;

        imageView.setImageBitmap(BitmapFactory.decodeResource(getApplicationContext().getResources(),
                R.raw.lead_720_405, options));

//        simarify();

    }

    public void simarify(View view) {
        simarify();
    }

    public void simarify() {
//        Bitmap img = BitmapFactory.decodeResource(getApplicationContext().getResources(),
////                R.id.imgview, options);
        Bitmap img = ((BitmapDrawable) imageView.getDrawable()).getBitmap();

        FaceDetector detector = new FaceDetector.Builder(getApplicationContext())
                .setProminentFaceOnly(true)
                .build();
>>>>>>> 142e029a5509001359ca019f3e96d2cdc4fc7f98

        //Image veiw for showing images

        Bitmap resultBitmap = Bitmap.createBitmap(img.getWidth(), img.getHeight(), Bitmap.Config.RGB_565);

        //Making canvas for image editing and then showing it later
        Canvas resultCanvas = new Canvas(resultBitmap);
        resultCanvas.drawBitmap(img, 0, 0, null);

<<<<<<< HEAD
        //It is the image that would be swapped with original
        simar_temp = BitmapFactory.decodeResource(
                getApplicationContext().getResources(),
                R.drawable.simar, options);
=======

        Bitmap simar_temp = BitmapFactory.decodeResource(
                getApplicationContext().getResources(),
                R.drawable.simar,
                options);

        Bitmap simarBitmap = Bitmap.createBitmap(simar_temp.getWidth(), simar_temp.getHeight(), Bitmap.Config.RGB_565);
>>>>>>> 142e029a5509001359ca019f3e96d2cdc4fc7f98

        //The actual face detector
        faceDetector = new
                FaceDetector.Builder(this).setTrackingEnabled(false)
                .build();
        if (!faceDetector.isOperational()) {
//            new AlertDialog.Builder(getApplicationContext()).setMessage("Could not set up the face detector!").show();d\\
            Toast.makeText(this, "Could not set up face detector!", Toast.LENGTH_SHORT).show();
            return;
        }

        detectFaceSwapAndShow(resultBitmap,resultCanvas);

    }

<<<<<<< HEAD

    //Face detection and swaping
    public void detectFaceSwapAndShow(Bitmap resultBitmap, Canvas resultCanvas){

        //Frame builder for editing image
        Frame frame = new Frame.Builder().setBitmap(base_image_temp).build();

        //All the faces detected-----------------------------------------------------
=======
        Frame frame = new Frame.Builder().setBitmap(img).build();

        //All the faces
>>>>>>> 142e029a5509001359ca019f3e96d2cdc4fc7f98
        SparseArray<Face> faces = faceDetector.detect(frame);

        //Making paint tool
        Paint myRectPaint = new Paint();
//        myRectPaint.setColor(70);

        //Running for loop on faces to edit and editing it one by one
        for (int i = 0; i < faces.size(); i++) {
            Face thisFace = faces.valueAt(i);

            float fwidth = thisFace.getWidth();
            float fheight = thisFace.getHeight();

            float x1 = thisFace.getPosition().x;
            float y1 = thisFace.getPosition().y;
<<<<<<< HEAD
=======
            float x2 = x1 + fwidth;
            float y2 = y1 + fheight;
>>>>>>> 142e029a5509001359ca019f3e96d2cdc4fc7f98

            resultCanvas.drawBitmap(getResizedBitmap(simar_temp, (int) thisFace.getWidth()-5, (int) thisFace.getHeight()+150), x1+10, y1-90, null);

<<<<<<< HEAD
=======
            float orig_height = (thisFace.getHeight());
            float final_height = (orig_height * (1 + (float) PATKA_RATIO));


            resultCanvas.drawBitmap(getResizedBitmap(simar_temp, (int) thisFace.getWidth(), (int) final_height), x1, y1 - (final_height - orig_height), null);

            Log.e("FACE", "FAce");
>>>>>>> 142e029a5509001359ca019f3e96d2cdc4fc7f98
        }

        imageView.setImageDrawable(new BitmapDrawable(getResources(), resultBitmap));
    }


    /*
     * Selects image from gallery and loads it for the processing
     * */
    public void selectImageFromGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, OPEN_FILE);

        simarify();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == OPEN_FILE) {
            Toast.makeText(this, "YES", Toast.LENGTH_LONG).show();
            Uri imageData = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(imageData);

                Bitmap temp = BitmapFactory.decodeStream(inputStream);

                //The base image for showing when app opens
                base_image_temp = temp;

                //The resulting bitmap after face swapping
                Bitmap resultBitmap = Bitmap.createBitmap(base_image_temp.getWidth(), base_image_temp.getHeight(), Bitmap.Config.RGB_565);

                //Making canvas for image editing and then showing it later----------
                Canvas resultCanvas = new Canvas(resultBitmap);
                resultCanvas.drawBitmap(base_image_temp, 0, 0, null);


                detectFaceSwapAndShow(resultBitmap,resultCanvas);



            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(this, "Wasnt able to open Image! :(", Toast.LENGTH_LONG).show();
            }

        }
    }


<<<<<<< HEAD



=======
    //Setting image veiw
    void setImageViewBitmap(Bitmap bitmap) {
        imageView.setImageDrawable(new BitmapDrawable(getResources(), bitmap));
    }
>>>>>>> 142e029a5509001359ca019f3e96d2cdc4fc7f98

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
