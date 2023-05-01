import sys

from PyQt5.QtWidgets import QMainWindow, QMessageBox
from PyQt5 import QtWidgets
import main_ui
from explanation_component import ExplanationComponent
from inference_engine import InferenceEngine
from knowledge_base import KnowledgeBase


class MainWindow(QMainWindow, main_ui.Ui_MainWindow):
    def __init__(self):
        super().__init__()
        self.setupUi(self)
        self.knowledge_base = KnowledgeBase()
        self.inference_engine = InferenceEngine(self.knowledge_base.list_nodes)
        self.explanation_component = ExplanationComponent()
        self.add_items_combobox()

        self.comboBox.currentTextChanged.connect(self.get_answer_1)
        self.comboBox_2.currentTextChanged.connect(self.get_answer_2)
        self.comboBox_3.currentTextChanged.connect(self.get_answer_2)
        self.comboBox_4.currentTextChanged.connect(self.get_answer_3)
        self.comboBox_5.currentTextChanged.connect(self.get_answer_4)


    def add_items_combobox(self):
        for i in range(3, 20):
            self.comboBox.addItem(self.knowledge_base.list_nodes[i].name)
            self.comboBox_2.addItem(self.knowledge_base.list_nodes[i].name)

        for i in range(0, 2):
            self.comboBox_3.addItem(self.knowledge_base.list_nodes[i].name)

        for i in range(34, 36):
            self.comboBox_4.addItem(self.knowledge_base.list_nodes[i].name)

        for i in range(3, 20):
            self.comboBox_5.addItem(self.knowledge_base.list_nodes[i].name)

    def get_answer_1(self):
        self.inference_engine.used = []
        nodes = self.inference_engine.height_request(self.comboBox.currentText())
        answer = f'Порода "{self.comboBox.currentText()}": \n'

        answer += '\n'.join(nodes)
        self.message_box(answer)

    def get_answer_2(self):
        self.inference_engine.used = []
        answer = f'Является ли "{self.comboBox_2.currentText()}" ' \
                 f'породой "{self.comboBox_3.currentText()}": \n'
        answer += str(self.inference_engine.type_breed_request(self.comboBox_2.currentText(),
                                                                   self.comboBox_3.currentText()))
        self.message_box(answer)


    def get_answer_3(self):
        self.inference_engine.used = []
        answer = f'У каких пород собак "{self.comboBox_4.currentText()}" ' \
                 f'? \n'
        answer += str(self.inference_engine.breed_ears_request(self.comboBox_4.currentText()))
        self.message_box(answer)

    def get_answer_4(self):
        self.inference_engine.used = []
        nodes = self.inference_engine.char_breed_request(self.comboBox_5.currentText())
        answer = f'Характеристики породы: "{self.comboBox_5.currentText()}": \n'
        answer += '\n'.join(nodes)
        self.message_box(answer)

    def info(self):
        self.message_box(self.explanation_component.get_logs(self.inference_engine.used))

    def message_box(self, answers):
        QMessageBox.about(self, "Title", answers)


if __name__ == '__main__':
    app = QtWidgets.QApplication(sys.argv)
    w = MainWindow()
    sys.exit(app.exec_())