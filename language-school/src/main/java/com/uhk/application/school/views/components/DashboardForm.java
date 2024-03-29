package com.uhk.application.school.views.components;

import com.uhk.application.school.controller.LanguageSchool;
import com.uhk.application.school.model.entity.Test;
import com.uhk.application.school.model.entity.User;
import com.uhk.application.school.model.security.AuthenticationService;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.*;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.component.template.Id;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

/**
 * A Designer generated component for the dashboard-form template.
 *
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("dashboard-form")
@JsModule("./views/dashboard-form.ts")
public class DashboardForm extends LitTemplate {

    @Autowired
    private LanguageSchool school;
    @Autowired
    private AuthenticationService authentication;

    @Id("gridTests")
    private Grid<Test> gridTests;

    @Id("divChart")
    private Div divChart;

    @PostConstruct
    public void init() {
        Optional<User> optionalUser = authentication.get();
        if (optionalUser.isPresent()) {
            User loggedInUser = optionalUser.get();
            gridTests.addColumn(Test::getIdTest).setHeader("ID testu").setSortable(true);
            gridTests.addColumn(Test::getQuestionCount).setHeader("Počet otázek").setSortable(true);
            List<Test> tests = school.getAllTestsByUserId(loggedInUser.getIdUser());
            gridTests.setItems(tests);

            addChartCorrectWrongAnswers(
                school.countCorrectAnswers(loggedInUser.getIdUser()),
                school.countWrongAnswers(loggedInUser.getIdUser())
            );
        }
    }

    /**
     * https://demo.vaadin.com/charts3/#PieChart
     * @param correctAnswers
     * @param wrongAnswers
     */
    private void addChartCorrectWrongAnswers(int correctAnswers, int wrongAnswers) {
        Chart chart = new Chart(ChartType.PIE);

        Configuration conf = chart.getConfiguration();

        conf.setTitle("Celkový poměr správných a špatných odpovědí ve Vašich testech:");
        Tooltip tooltip = new Tooltip();
        tooltip.setValueDecimals(1);
        conf.setTooltip(tooltip);

        PlotOptionsPie plotOptions = new PlotOptionsPie();
        plotOptions.setAllowPointSelect(true);
        plotOptions.setCursor(Cursor.POINTER);
        plotOptions.setShowInLegend(true);
        conf.setPlotOptions(plotOptions);

        DataSeries answers = new DataSeries();
        DataSeriesItem correct = new DataSeriesItem("Správných", correctAnswers);
        correct.setSliced(false);
        correct.setSelected(true);
        answers.add(correct);
        answers.add(new DataSeriesItem("Špatných", wrongAnswers));
        conf.setSeries(answers);
        chart.setVisibilityTogglingDisabled(true);
        divChart.add(chart);
    }


    /**
     * Creates a new DashboardForm.
     */
    public DashboardForm() {
        // You can initialise any data required for the connected UI components here.
    }

}
