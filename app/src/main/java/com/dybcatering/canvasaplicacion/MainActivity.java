package com.dybcatering.canvasaplicacion;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.graphics.CanvasView;

import static com.dybcatering.canvasaplicacion.R.mipmap.ic_launcher;

public class MainActivity extends AppCompatActivity {

	private CanvasView canvas ;

	private Button texto, undo, redo, negro, rojo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		canvas = findViewById(R.id.canvas);
		canvas.setMode(CanvasView.Mode.DRAW);
		texto = findViewById(R.id.btncolor);
		undo = findViewById(R.id.btnundo);
		redo = findViewById(R.id.btnredo);
		negro = findViewById(R.id.btncambianegro);
		rojo = findViewById(R.id.btncambiarojo);


		texto.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {


				AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
				builder.setTitle("Agregar nuevo texto");

// Set up the input
				final EditText input = new EditText(MainActivity.this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
				input.setInputType(InputType.TYPE_CLASS_TEXT);
				builder.setView(input);

// Set up the buttons
				builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						String m_Text = input.getText().toString();

						canvas.setText(m_Text);
						canvas.setFontSize(50F);

					}
				});
				builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}
				});

				builder.show();
				canvas.setMode(CanvasView.Mode.DRAW);

			}
		});

		undo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				canvas.setMode(CanvasView.Mode.DRAW);
				canvas.undo();
			}
		});

		redo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				canvas.setMode(CanvasView.Mode.DRAW);
				canvas.redo();
			}
		});
		negro.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				canvas.setPaintStrokeColor(Color.BLACK);
			}
		});
		rojo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				canvas.setPaintStrokeColor(Color.RED);
			}
		});

	}
}
