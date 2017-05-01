package com.projects.thirtyseven.activecameraviewer2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    GridLayout cameraGridLayout;
    ArrayList<FrameLayout> cameraFrameLayoutButtons; //ArrayList в котором будут находится кнопки
    ArrayList<String> listOfCameraNames;  //ArrayList в котором будут находится имена камер

    View.OnClickListener onClickListener;

    private CameraController cameraController;
    private CameraModel cameraModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        refreshView();  //Мы используем MainActivity как Представление следоватьельно мы должны его обновить

        onClickListener = new View.OnClickListener() { //МультиЛистенер т.к. мы имеем множество кнопок
            @Override
            public void onClick(View v) {
                cameraController.clickCamera(String.valueOf(v.getId()));
                //При нажатии контроллер мы передаём String'овое значение т.к. название камеры и Id совпадают
                refreshView(); //Обновляем View
            }
        };
    }

    private void refreshView() {
        //В методе For each : - двоеточие используется для Изьятия единного из множества
        // cameraFrameLayoutButtons - список всех кнопок
        for (FrameLayout button : cameraFrameLayoutButtons) {
            ImageView image = (ImageView) button.getChildAt(0);
            // Мы определили Image для более удоьного использования в дальнейшем.
            //getChildAt - получение с FrameLayout ребенка под индексом 0. В нашем случае это Картинка Статуса камеры
            int cameraStatus = cameraModel.getCameraStatus(String.valueOf(button.getId()));
            //Определяем статус взяв с модели Id.

            //В зависимости от статуса меняем картинку статуса камеры
            if (cameraStatus == 0) image.setImageResource(R.drawable.grey_status);
            else if (cameraStatus == 1) image.setImageResource(R.drawable.yellow_status);
            else if (cameraStatus == 2) image.setImageResource(R.drawable.red_status);
        }
    }

    private void init() {
        cameraFrameLayoutButtons = new ArrayList<>();
        listOfCameraNames = new ArrayList<>();
        //Инициализируем наш ArrayList'ы

        cameraGridLayout = (GridLayout) findViewById(R.id.cameraGridLayout);
        //Layout с которым мы работаем

        //Цикл благодаря которому мы можем инициализировать множество элементов и засетить на них Listener'ы
        //Не засоряя код кучей повторяющихся строчек
        for (int i = 0; i < cameraGridLayout.getChildCount(); i++) {
            //С layout'а мы получаем количество детей, и по количеству выполняем цикл
            FrameLayout button = (FrameLayout) cameraGridLayout.getChildAt(i);
            //Получаем ребёнка по его индексу
            cameraFrameLayoutButtons.add(button);
            listOfCameraNames.add(String.valueOf(button.getId()));
            //Добавили элемент в ArrayList
            button.setOnClickListener(onClickListener);
            //Добавили Listener
        }

        cameraModel = new CameraModel(listOfCameraNames);
        cameraController = new CameraController(cameraModel);
    }
}