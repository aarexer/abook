package com.github.aarexer.address.view;

import com.github.aarexer.address.MainApp;
import com.github.aarexer.address.model.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.text.DateFormatSymbols;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by rexer on 23.02.15.
 */


public class StatisticBarController implements Initializable
{
    private MainApp mainApp;

    @FXML
    BarChart<String, Integer> barChart;

    @FXML
    CategoryAxis xAxis;

    private ObservableList<String> months = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        months.addAll(DateFormatSymbols.getInstance(Locale.ENGLISH).getMonths());

        xAxis.setCategories(months);
    }

    public void setDatatoChart(List<Person> persons)
    {
        int[] monthCount = new int[12];
        for (Person p : persons)
        {
            int m = p.getBirthday().getMonthValue() - 1;
            monthCount[m]++;
        }

        XYChart.Series<String, Integer> series = new XYChart.Series<>();

        for (int i = 0; i < monthCount.length; i++)
        {
            series.getData().add(new XYChart.Data<>(months.get(i), monthCount[i]));
        }

        barChart.getData().add(series);
    }

    public void setMainApp(MainApp mainApp)
    {
        this.mainApp = mainApp;
    }

}
