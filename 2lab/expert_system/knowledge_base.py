import copy
import json
import yaml
from Elements.fact import Fact
from Elements.rule import Rule


class KnowledgeBase:
    def __init__(self):
        self.rules = []
        self.facts = []

    def load(self):
        print('load knowledge base')
        # with open('rules.json', 'r') as file:
        #     data = json.load(file)
        with open('rules.yaml', 'r') as file:
            data = yaml.load(file, Loader=yaml.FullLoader)

        for item in data:
            name_rule = item.get('rule')
            question = item.get('question')
            priority = item.get('priority')
            value = item.get('value')
            assert_fact = item.get('assertFact')
            dixt = item.get('condition')
            facts = dixt.get('facts')
            # print( 'facts: ', facts)
            # print('rule -', item.get('rule'))
            fact_from_rule = []
            newArr = []
            amount_facts = 0
            for fact in facts:

                if fact.get('operation') is not None:
                    operator = fact.get('operation')
                    nested_facts = fact.get('facts')
                    # print('Operator: ', operator)
                    newArr.append('operand:' + operator)
                    for nest_fact in nested_facts:
                        # print(nest_fact)
                        newArr.append(nest_fact)
                else:
                    newArr.append(fact)
                amount_facts += 1
                fact_from_rule = newArr
                # fact_from_rule.append(newArr)

            rule = Rule(name_rule, question, priority, value, fact_from_rule, assert_fact, amount_facts)

            # item.get('assertFact'), item.get('priority'), item.get('value'))
            # self.facts.append(fact)
            self.rules.append(rule)

        self.print_rules()
        print('knowledgebase loaded')

    def get_name_rule(self, rule):
        return rule.name_rule

    def remove_rule(self, pos):
        print('remove rule #', pos)
        self.rules.pop(pos)

    def remove_rules(self, rules):
        for rule in rules:
            print('delete rule -', rule.name_rule)
            self.rules.remove(rule)

    def get_rule(self, rule):
        return rule

    def get_rules(self):
        return self.rules

    def print_name_rules(self):
        for rule in self.rules:
            print(rule.name_rule)

    def print_rules(self):
        for rule in self.rules:
            rule.print_rule()
