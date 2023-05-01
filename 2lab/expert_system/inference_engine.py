import functools

import knowledge_base
from Elements.fact import Fact
from Elements.rule import Rule

from explanation_component import ExplanationComponent
from knowledge_base import KnowledgeBase
from working_memory import WorkingMemory


def get_rule(rules, id):
    i = 0
    for rule in rules:
        if i == id:
            return rule
        break


class InferenceEngine:

    def __init__(self):
        self.knowledge_base = KnowledgeBase()
        self.working_memory = WorkingMemory()
        self.explanation_component = ExplanationComponent()
        self.current_rule = None
        self.answers = None
        self.question = None
        self.is_answer = False
        self.knowledge_base.load()
        self.set_new_question()

    def set_new_question(self):
        print('inference engine:')
        current_facts = self.working_memory.get_current_facts()
        print(len(current_facts))
        if current_facts.__len__() == 0:
            rule = get_rule(self.knowledge_base.get_rules(), 0)                         
            rule.facts[0].get('value'))
            self.current_rule = rule
            self.question = rule.question
            self.answers = rule.value
        else:
            definded = self.choose_rule()
            if not definded:
                rule = get_rule(self.knowledge_base.get_rules(), 0)
                print('NEW rule : ', rule.question)
                self.current_rule = rule
                self.question = rule.question
                self.answers = rule.value
        self.knowledge_base.remove_rule(0)
        self.get_current_facts()


    def choose_rule(self):
        print('choose rule: ')
        definded = False
        delete_rules = []
        i = 0
        for rule in self.knowledge_base.rules:
            # print('rule -', rule.name_rule, 'nested facts - ', rule.amount_facts)
            flag = []
            oper = ''
            temp = 0
            # цикл по текущим фактам в рабочей памяти
            for fact in self.working_memory.current_facts:
                # цикл по вложенным фактам
                for nest_fact in rule.facts:
                    cond = False
                    if nest_fact == 'operand:||':
                        oper = '||'
                        continue
                    elif nest_fact == 'operand:&':
                        oper = '&'
                        continue
                    elif nest_fact == 'operand:||&':
                        oper = '||&'
                        continue
                    # print("nested_fact: ", nest_fact.get('name'), nest_fact.get('value'))
                    if oper == '||' and temp < 2:
                        temp += 1
                        if nest_fact.get('name') == fact.name:
                            if nest_fact.get('value') == fact.value:
                                if temp == 1:
                                    temp = 0
                                    cond = True
                                    break
                            elif temp == 1:
                                continue
                            else:
                                break
                    elif nest_fact.get('name') == fact.name:
                        if nest_fact.get('value') == fact.value:
                            cond = True
                            break
                        else:
                            delete_rules.append(rule)
                if temp == 2 or temp == 0:
                    if cond:
                        flag.append(1)
                    else:
                        flag.append(0)
                if temp == 2:
                    temp = 0

            print(flag, sum(flag))
            if sum(flag) + 1 == rule.amount_facts:
                if not rule.value:
                    self.is_answer = True
                    print('rule value - ', rule.value)
                    print(rule.assert_fact.get('value'))
                    self.question = 'breed detected\n' + rule.assert_fact.get('value')
                    definded = True
                    break
            i += 1

        self.knowledge_base.print_name_rules()
        delete_rules = list(set(delete_rules))
        self.knowledge_base.remove_rules(delete_rules)
        return definded

    def get_current_facts(self):
        work_memory = self.working_memory.current_facts
        print('current facts:')
        for fact in work_memory:
            print(fact.name, fact.value)

    def set_user_answer(self, value):
        name_fact = self.current_rule.get_assert_fact().get('name')
        self.working_memory.add_fact(name_fact, value)

    def get_logs(self):
        return self.explanation_component.get_logs(self.working_memory.current_facts)