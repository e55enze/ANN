import sys
from PyQt5.QtWidgets import QMainWindow, QMessageBox
from PyQt5 import QtWidgets
import main_ui
from explanation_component import ExplanationComponent
from inference_engine import InferenceEngine


class MainWindow(QMainWindow, main_ui.Ui_MainWindow):
    def __init__(self):
        super().__init__()
        self.setupUi(self)

        # self.explanation_component = ExplanationComponent()
        self.inference_engine = InferenceEngine()

        self.pushButton.clicked.connect(self.enter)
        self.pushButton_3.clicked.connect(self.restart)
        self.pushButton_4.clicked.connect(self.log)

    def enter(self):
        print('Enter')
        value = self.comboBox.currentText()
        print(value)
        self.inference_engine.set_user_answer(value)
        self.inference_engine.set_new_question()
        self.comboBox.clear()
        self.label_2.setText('')
        if self.inference_engine.isAnswer:
            print('IS ANSWER')
        else:
            # self.label_2.setText(self.inference_engine.question)
            self.next()

    def message_box(self, answers):
        QMessageBox.about(self, "Title", answers)

    def restart(self):
        self.comboBox.clear()
        print('restart')
        # self.label_2.setText('')
        # self.inference_engine = InferenceEngine()
        self.inference_engine = InferenceEngine()
        self.next()

    def log(self):
        print('log')
        # logs = self.inference_engine.get_logs()
        # self.message_box(logs)

    def next(self):
        print('next')
        self.label_2.setText(self.inference_engine.question)
        self.comboBox.addItems(self.inference_engine.answers)


if __name__ == '__main__':
    app = QtWidgets.QApplication(sys.argv)
    w = MainWindow()
    sys.exit(app.exec_())