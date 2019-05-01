package me.kindeep.simarmemegenerator;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.MultiProcessor;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;
import com.google.android.gms.vision.face.LargestFaceFocusingProcessor;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FaceDetector detector = new FaceDetector.Builder(getApplicationContext())
                .setProminentFaceOnly(true)
                .build();

//        detector.setProcessor(
//                new LargestFaceFocusingProcessor(
//                        detector,
//                        new FaceTracker())
//        );
//
//        CameraSource cameraSource = new CameraSource.Builder(context, detector)
//                .setFacing(CameraSource.CAMERA_FACING_FRONT)
//                .setRequestedPreviewSize(320, 240)
//                .build()
//                .start();
//        Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.obama);
//
////        FaceDetector detector = new FaceDetector(image.getWidth(), image.getHeight(), 5);
//        FaceDetector detector = new FaceDetector.Builer(context);

//        FaceDetector.Face[] faces = new FaceDetector.Face[5];
//
//        detector.findFaces(image, faces);


//        Log.v("YOOOO", Integer.toString(faces.length) + faceToString(faces));


    }
}
