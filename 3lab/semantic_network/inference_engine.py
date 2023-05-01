class InferenceEngine:
    def __init__(self, nodes):
        self.nodes = nodes
        self.used = []

    def add_combination_used(self, connection, node_name):
        self.used.append(f'connection: {connection} \n')
        self.used.append(f'node: {node_name} \n')

    def height_request(self, breed):
        breed_node = None
        print('Height request')
        for node in self.nodes:
            if node.name == breed:
                breed_node = node

        self.used.append(f'node: {breed_node.name} \n')
        success_nodes = []
        for connection in breed_node.connections:
            if connection.type == 'has-height':
                self.used.append(f'connection: has-height \n')
                self.used.append(f'node: {connection.output_node.name} \n')
                success_nodes.append(connection.output_node.name)
        return success_nodes

    def type_breed_request(self, breed, type_breed):
        breed_node = None
        print('Type breed')
        for node in self.nodes:
            if node.name == breed:
                breed_node = node

        self.used.append(f'node: {breed_node.name} \n')

        for connection in breed_node.connections:
            if connection.type == 'IS_A':
                print(connection.input_node.name, connection.output_node.name)
                if connection.input_node.name == breed:
                    if connection.output_node.name == type_breed:
                        self.add_combination_used(breed, connection.output_node.name)
                        self.used.append('True \n')
                        return True
        self.used.append('False \n')
        return False

    def breed_ears_request(self, ears):
        current_node = None
        print('Ears of breed')
        for node in self.nodes:
            if node.name == ears:
                current_node = node
        success_nodes = []
        for connection in current_node.connections:
            if connection.type == 'has-ears':
                # self.add_combination_used(connection.input_node.name)
                success_nodes.append(connection.input_node.name)
                # print(connection.input_node.name, connection.output_node.name)
        return success_nodes

    def char_breed_request(self, breed):
        print('Characteristics breed request')
        char_node = None
        for node in self.nodes:
            if node.name == breed:
                char_node = node

        success_nodes = []
        for connection in char_node.connections:
            print(connection.output_node.name)
            if connection.type == 'IS_A':
                success_nodes.append('Тип породы: ' + connection.output_node.name)
            else:
                success_nodes.append(connection.output_node.name)
        return success_nodes
