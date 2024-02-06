package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button gestureButton;
    private TextView nameBar;
    private GestureDetector gestureDetector;
    private ScaleGestureDetector scaleGestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gestureButton = findViewById(R.id.gestureButton);
        nameBar = findViewById(R.id.nameBar);
        gestureDetector = new GestureDetector(this, new ExtendedGestureListener());
        scaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());

        gestureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Button Clicked");
            }
        });

        gestureButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                scaleGestureDetector.onTouchEvent(event);
                return gestureDetector.onTouchEvent(event);
            }
        });
    }

    private class ExtendedGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            showToast("Single Tap");
            return true;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            showToast("Double Tap");
            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            showToast("Hold Gesture");
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float deltaX = e2.getX() - e1.getX();
            float deltaY = e2.getY() - e1.getY();

            if (Math.abs(deltaX) > Math.abs(deltaY)) {
                // Horizontal swipe
                if (deltaX > 0) {
                    showToast("Swipe Right");
                } else {
                    showToast("Swipe Left");
                }
            } else {
                // Vertical swipe
                if (deltaY > 0) {
                    showToast("Swipe Down");
                } else {
                    showToast("Swipe Up");
                }
            }
            return true;
        }
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            float scaleFactor = detector.getScaleFactor();
            showToast("Pinch Gesture - Scale Factor: " + scaleFactor);
            return true;
        }

        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            return true;
        }

        @Override
        public void onScaleEnd(ScaleGestureDetector detector) {
            // Handle the end of scaling if needed
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
