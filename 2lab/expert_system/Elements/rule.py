from Elements.fact import Fact


class Rule:

    def __init__(self, name_rule, question, priority, value, facts, assert_fact):
        self.name_rule = name_rule
        self.question = question
        self.assert_fact = assert_fact
        self.is_used = False
        self.priority = priority
        self.value = value
        self.facts = facts
        self.nested_facts = []

    def add_condition(self, name, value):
        # fact = Fact(name, value)
        str = f'{name}, {value}'
        self.own_facts.append(str)

    def print_rule(self):
        print(self.name_rule, self.question, self.priority, self.value, self.assert_fact)
        for fact in self.facts:
            print(fact)

    def get_name_rule(self, rule):
        return self.name_rule

    def get_question(self):
        return self.question

    def get_value(self):
        return self.value
