package srtnglgrthms.controller;

import java.util.Arrays;
import java.util.List;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.util.Duration;
import srtnglgrthms.model.Algorithms;
import srtnglgrthms.model.ForwardRadix;
import javafx.geometry.Bounds;

public class OverviewController {
	@FXML
	private BarChart<String, Integer> barChart;
	@FXML
	private Button stepBtn;
	@FXML
	private Button animBtn;
	@FXML
	private ListView<String> algorithmList;

	XYChart.Series<String, Integer> series;
	Timeline tl;

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		series = new XYChart.Series<>();
		tl = new Timeline();
		initChart();
		initBtns();
		initList();
		setAnimation();
		setLabels();
	}

	private void initChart() {
		int[] numbers = Algorithms.getNumbers();
		for (int i = 0; i < numbers.length; ++i) {
			final XYChart.Data<String, Integer> data = new XYChart.Data("t[" + i + "]", numbers[i]);
			data.nodeProperty().addListener(new ChangeListener<Node>() {
		        public void changed(ObservableValue<? extends Node> ov, Node oldNode, final Node node) {
		          if (node != null) {
		            //setNodeStyle(data);
		            displayLabelForData(data);
		          } 
		        }
		      });
			 series.getData().add(data);
		}

		barChart.getData().add(series);
		for (int i = 0; i < series.getData().size(); i++) {
			Node n = series.getData().get(i).getNode();
			n.setStyle("-fx-bar-fill: orange;");
		}

	}

	private void initList() {
		List<String> values = Arrays.asList("Buborékrendezés",
				"Beszúró rendezés", "Kupacrendezés", "Versenyrendezés",
				"Gyorsrendezés", "Shell rendezés", "Radix \"elõre\"",
				"Radix \"hátra\"");
		algorithmList.setItems(FXCollections.observableList(values));
	}

	private void setAnimation() {
		tl.getKeyFrames().add(
				new KeyFrame(Duration.millis(100),
						new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent actionEvent) {
								// BubbleSort.BubbleStep(series.getData());
								// InsertionSort.InsertionStep(series.getData());
								ForwardRadix.RadixStep(series.getData());
							}
						}));
	}

	private void setLabels() {
		int[] numbers = Algorithms.getNumbers();
		for (int i = 0; i < numbers.length; ++i) {
			displayLabelForData(series.getData().get(i));
		}
	}

	private void displayLabelForData(XYChart.Data<String, Integer> data) {
		final Node node = data.getNode();
		final Text dataText = new Text(data.getYValue() + "");
		node.parentProperty().addListener(new ChangeListener<Parent>() {
			@Override
			public void changed(ObservableValue<? extends Parent> ov,
					Parent oldParent, Parent parent) {
				Group parentGroup = (Group) parent;
				parentGroup.getChildren().add(dataText);
			}
		});
		node.boundsInParentProperty().addListener(new ChangeListener<Bounds>() {
			@Override
			public void changed(ObservableValue<? extends Bounds> ov,
					Bounds oldBounds, Bounds bounds) {
				dataText.setLayoutX(Math.round(bounds.getMinX()
						+ bounds.getWidth() / 2 - dataText.prefWidth(-1) / 2));
				dataText.setLayoutY(Math.round(bounds.getMinY()
						- dataText.prefHeight(-1) * 0.5));
			}
		});
	}

	private void initBtns() {
		stepBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// BubbleSort.BubbleStep(series.getData());
				// InsertionSort.InsertionStep(series.getData());
				ForwardRadix.RadixStep(series.getData());
			}
		});

		animBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				tl.setCycleCount(Animation.INDEFINITE);
				tl.play();
			}
		});
	}
}