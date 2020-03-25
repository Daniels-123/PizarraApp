package com.dybcatering.canvasaplicacion;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.graphics.CanvasView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import static com.dybcatering.canvasaplicacion.R.mipmap.ic_launcher;

public class MainActivity extends AppCompatActivity {

	private CanvasView canvas ;

	private Button texto, undo, redo, negro, rojo, rectangulo, circulo, btnborrador, btnqu, agregarimagen;

	ImageView mImg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		canvas = findViewById(R.id.canvas);
		texto = findViewById(R.id.btncolor);
		undo = findViewById(R.id.btnundo);
		redo = findViewById(R.id.btnredo);
		negro = findViewById(R.id.btncambianegro);
		rojo = findViewById(R.id.btncambiarojo);
		rectangulo = findViewById(R.id.rectangulo);
		circulo = findViewById(R.id.btncirculo);
		btnborrador = findViewById(R.id.btnborrador);
		btnqu = findViewById(R.id.btnqu);



		texto.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				canvas.setMode(CanvasView.Mode.TEXT);

// Setter

				AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
				builder.setTitle("Agregar nuevo texto");

				final EditText input = new EditText(MainActivity.this);
				input.setInputType(InputType.TYPE_CLASS_TEXT);
				builder.setView(input);
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
			//	canvas.setMode(CanvasView.Mode.DRAW);

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
				canvas.setPaintStrokeWidth(3F);
				canvas.setMode(CanvasView.Mode.DRAW);
				canvas.setDrawer(CanvasView.Drawer.PEN);
				canvas.setPaintStrokeColor(Color.BLACK);
			}
		});
		rojo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				canvas.setPaintStrokeWidth(3F);
				canvas.setMode(CanvasView.Mode.DRAW);
				canvas.setDrawer(CanvasView.Drawer.PEN);
				canvas.setPaintStrokeColor(Color.RED);
			}
		});
		rectangulo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				canvas.setMode(CanvasView.Mode.DRAW);
				canvas.setPaintStrokeColor(Color.BLACK);
				canvas.setPaintStrokeWidth(3F);
				canvas.setDrawer(CanvasView.Drawer.RECTANGLE);
			}
		});

		circulo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				canvas.setMode(CanvasView.Mode.DRAW);
				canvas.setPaintStrokeColor(Color.BLACK);
				canvas.setPaintStrokeWidth(3F);
				canvas.setDrawer(CanvasView.Drawer.CIRCLE);
			}
		});
		btnborrador.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				canvas.setMode(CanvasView.Mode.DRAW);
				canvas.setDrawer(CanvasView.Drawer.PEN);
				canvas.setPaintStrokeColor(Color.WHITE);
				canvas.setPaintStrokeWidth(50F);
			}
		});
		btnqu.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				canvas.setMode(CanvasView.Mode.DRAW);
				canvas.setDrawer(CanvasView.Drawer.QUADRATIC_BEZIER);
			}
		});
		/*agregarimagen.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Bitmap bitmap = canvas.getBitmap();

				mImg.setImageBitmap(bitmap);

			}
		});*/


	}



	private File savebitmap(String filename) {
		String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
		OutputStream outStream = null;

		File file = new File(filename + ".png");
		if (file.exists()) {
			file.delete();
			file = new File(extStorageDirectory, filename + ".png");
			Log.e("file exist", "" + file + ",Bitmap= " + filename);
		}
		try {
			// make a new bitmap from your file
			Bitmap bitmap = this.canvas.getBitmap();

			//Bitmap bitmap = BitmapFactory.decodeFile(file.getName());

			outStream = new FileOutputStream(file);
			bitmap.compress(Bitmap.CompressFormat.PNG, 100, outStream);
			outStream.flush();
			outStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Log.e("file", "" + file);
		return file;

	}

}
