from Elements.fact import Fact

class WorkingMemory:
    def __init__(self):
        self.current_facts = []
        self.added_facts = []

    def add_fact(self, name, value):
        fact = Fact(name, value)
        print(f'Added fact: ', fact.name, ' with value: ', fact.value)
        self.current_facts.append(fact)
        # self.current_facts_names.append(name)

    def get_current_facts(self):
        return self.current_facts