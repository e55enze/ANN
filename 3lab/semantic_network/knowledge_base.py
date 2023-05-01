from connection import Connection
from node import Node


class KnowledgeBase:
    def __init__(self):
        self.list_nodes = []
        self.create_nodes()
        self.create_connections()

    def create_nodes(self):
        self.list_nodes.append(Node('Короткошерстная'))             # 0
        self.list_nodes.append(Node('Длинношерстная'))              # 1
        self.list_nodes.append(Node('Порода'))                      # 2
        self.list_nodes.append(Node('Бульдог'))                     # 3
        self.list_nodes.append(Node('Гончая'))                      # 4
        self.list_nodes.append(Node('Мопс'))                        # 5
        self.list_nodes.append(Node('Чихуахуа'))                    # 6
        self.list_nodes.append(Node('Датский дог'))                 # 7
        self.list_nodes.append(Node('Фоксхаунд'))                   # 8
        self.list_nodes.append(Node('Кокер-спаниэль'))              # 9
        self.list_nodes.append(Node('Чау-Чау'))                     # 10
        self.list_nodes.append(Node('Такса'))                       # 11
        self.list_nodes.append(Node('Йоркширский-Терьер'))          # 12
        self.list_nodes.append(Node('Большой Вандейский грифон'))   # 13
        self.list_nodes.append(Node('Ирландский сеттер'))           # 14
        self.list_nodes.append(Node('Колли'))                       # 15
        self.list_nodes.append(Node('Кавказская овчарка'))          # 16
        self.list_nodes.append(Node('Ирландский волкодав'))         # 17
        self.list_nodes.append(Node('Ньюфаундленд'))                # 18
        self.list_nodes.append(Node('Сенбернар'))                   # 19
        self.list_nodes.append(Node('Рост породы: менее 50 см'))    # 20
        self.list_nodes.append(Node('Рост породы: более 50 см'))    # 21
        self.list_nodes.append(Node('Рост породы: менее 70 см'))    # 22
        self.list_nodes.append(Node('Рост породы: более 70 см'))    # 23
        self.list_nodes.append(Node('Вес породы: менее 30 кг'))     # 24
        self.list_nodes.append(Node('Вес породы: более 30 кг'))     # 25
        self.list_nodes.append(Node('Вес породы: менее 50 кг'))     # 26
        self.list_nodes.append(Node('Вес породы: более 50 кг'))     # 27
        self.list_nodes.append(Node('Тело короткое'))               # 28
        self.list_nodes.append(Node('Тело длинное'))                # 29
        self.list_nodes.append(Node('Хвост короткий'))              # 30
        self.list_nodes.append(Node('Хвост длинный'))               # 31
        self.list_nodes.append(Node('Добрый характер'))             # 32
        self.list_nodes.append(Node('Своенравный характер'))        # 33
        self.list_nodes.append(Node('Короткие уши'))                # 34
        self.list_nodes.append(Node('Длинные уши'))                 # 35
        self.list_nodes.append(Node('Однородный окрас'))            # 36
        self.list_nodes.append(Node('Смешанный окрас'))             # 37
        self.list_nodes.append(Node('Светлый окрас'))               # 38
        self.list_nodes.append(Node('Темный окрас'))                # 39


    def create_connections(self):

        Connection('AKO', self.list_nodes[0], self.list_nodes[2])
        Connection('AKO', self.list_nodes[1], self.list_nodes[2])
        # Породы
        Connection('IS_A', self.list_nodes[3], self.list_nodes[0]).add_connection()
        Connection('IS_A', self.list_nodes[4], self.list_nodes[0]).add_connection()
        Connection('IS_A', self.list_nodes[5], self.list_nodes[0]).add_connection()
        Connection('IS_A', self.list_nodes[6], self.list_nodes[0]).add_connection()
        Connection('IS_A', self.list_nodes[7], self.list_nodes[0]).add_connection()
        Connection('IS_A', self.list_nodes[8], self.list_nodes[0]).add_connection()
        Connection('IS_A', self.list_nodes[9], self.list_nodes[1]).add_connection()
        Connection('IS_A', self.list_nodes[10], self.list_nodes[1]).add_connection()
        Connection('IS_A', self.list_nodes[11], self.list_nodes[1]).add_connection()
        Connection('IS_A', self.list_nodes[12], self.list_nodes[1]).add_connection()
        Connection('IS_A', self.list_nodes[13], self.list_nodes[1]).add_connection()
        Connection('IS_A', self.list_nodes[14], self.list_nodes[1]).add_connection()
        Connection('IS_A', self.list_nodes[15], self.list_nodes[1]).add_connection()
        Connection('IS_A', self.list_nodes[16], self.list_nodes[1]).add_connection()
        Connection('IS_A', self.list_nodes[17], self.list_nodes[1]).add_connection()
        Connection('IS_A', self.list_nodes[18], self.list_nodes[1]).add_connection()
        Connection('IS_A', self.list_nodes[19], self.list_nodes[1]).add_connection()

        # Рост породы
        Connection('has-height', self.list_nodes[3], self.list_nodes[20]).add_connection()
        Connection('has-height', self.list_nodes[4], self.list_nodes[20]).add_connection()
        Connection('has-height', self.list_nodes[5], self.list_nodes[20]).add_connection()
        Connection('has-height', self.list_nodes[6], self.list_nodes[20]).add_connection()
        Connection('has-height', self.list_nodes[7], self.list_nodes[21]).add_connection()
        Connection('has-height', self.list_nodes[8], self.list_nodes[21]).add_connection()
        Connection('has-height', self.list_nodes[9], self.list_nodes[20]).add_connection()
        Connection('has-height', self.list_nodes[10], self.list_nodes[20]).add_connection()
        Connection('has-height', self.list_nodes[11], self.list_nodes[20]).add_connection()
        Connection('has-height', self.list_nodes[12], self.list_nodes[20]).add_connection()
        Connection('has-height', self.list_nodes[13], self.list_nodes[21]).add_connection()
        Connection('has-height', self.list_nodes[13], self.list_nodes[22]).add_connection()
        Connection('has-height', self.list_nodes[14], self.list_nodes[21]).add_connection()
        Connection('has-height', self.list_nodes[14], self.list_nodes[22]).add_connection()
        Connection('has-height', self.list_nodes[15], self.list_nodes[21]).add_connection()
        Connection('has-height', self.list_nodes[15], self.list_nodes[22]).add_connection()
        Connection('has-height', self.list_nodes[16], self.list_nodes[21]).add_connection()
        Connection('has-height', self.list_nodes[16], self.list_nodes[22]).add_connection()
        Connection('has-height', self.list_nodes[17], self.list_nodes[21]).add_connection()
        Connection('has-height', self.list_nodes[17], self.list_nodes[23]).add_connection()
        Connection('has-height', self.list_nodes[18], self.list_nodes[21]).add_connection()
        Connection('has-height', self.list_nodes[18], self.list_nodes[23]).add_connection()
        Connection('has-height', self.list_nodes[19], self.list_nodes[21]).add_connection()
        Connection('has-height', self.list_nodes[19], self.list_nodes[23]).add_connection()

        # Вес породы
        Connection('has-weight', self.list_nodes[15], self.list_nodes[24]).add_connection()
        Connection('has-weight', self.list_nodes[16], self.list_nodes[25]).add_connection()
        Connection('has-weight', self.list_nodes[7], self.list_nodes[27]).add_connection()
        Connection('has-weight', self.list_nodes[8], self.list_nodes[26]).add_connection()

        # Тело породы
        Connection('has-weight', self.list_nodes[5], self.list_nodes[28]).add_connection()
        Connection('has-weight', self.list_nodes[6], self.list_nodes[29]).add_connection()

        # Хвост
        Connection('has-tail', self.list_nodes[3], self.list_nodes[30]).add_connection()
        Connection('has-tail', self.list_nodes[4], self.list_nodes[31]).add_connection()
        Connection('has-tail', self.list_nodes[5], self.list_nodes[31]).add_connection()
        Connection('has-tail', self.list_nodes[6], self.list_nodes[31]).add_connection()

        # Характер
        Connection('has-character', self.list_nodes[9], self.list_nodes[32]).add_connection()
        Connection('has-character', self.list_nodes[12], self.list_nodes[32]).add_connection()
        Connection('has-character', self.list_nodes[10], self.list_nodes[33]).add_connection()
        Connection('has-character', self.list_nodes[11], self.list_nodes[33]).add_connection()

        # Уши
        Connection('has-ears', self.list_nodes[5], self.list_nodes[34]).add_connection()
        Connection('has-ears', self.list_nodes[6], self.list_nodes[34]).add_connection()
        Connection('has-ears', self.list_nodes[10], self.list_nodes[34]).add_connection()
        Connection('has-ears', self.list_nodes[12], self.list_nodes[34]).add_connection()
        Connection('has-ears', self.list_nodes[15], self.list_nodes[34]).add_connection()
        Connection('has-ears', self.list_nodes[16], self.list_nodes[34]).add_connection()
        Connection('has-ears', self.list_nodes[4], self.list_nodes[35]).add_connection()
        Connection('has-ears', self.list_nodes[9], self.list_nodes[35]).add_connection()
        Connection('has-ears', self.list_nodes[11], self.list_nodes[35]).add_connection()
        Connection('has-ears', self.list_nodes[13], self.list_nodes[35]).add_connection()
        Connection('has-ears', self.list_nodes[14], self.list_nodes[35]).add_connection()

        # Окрас
        Connection('has-color', self.list_nodes[17], self.list_nodes[36]).add_connection()
        Connection('has-color', self.list_nodes[18], self.list_nodes[36]).add_connection()
        Connection('has-color', self.list_nodes[19], self.list_nodes[37]).add_connection()
        Connection('has-color', self.list_nodes[13], self.list_nodes[38]).add_connection()
        Connection('has-color', self.list_nodes[17], self.list_nodes[38]).add_connection()
        Connection('has-color', self.list_nodes[14], self.list_nodes[39]).add_connection()
        Connection('has-color', self.list_nodes[18], self.list_nodes[39]).add_connection()
