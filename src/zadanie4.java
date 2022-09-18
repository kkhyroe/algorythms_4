class LinkNode {
    LinkNode next;    // указатель на следующий элемент
    int data;            // данные

    public static LinkNode toString(LinkNode linkNode) {
        if (linkNode != null) {
            System.out.println(linkNode.data);
        }
        return null;
    }
}

class DoubleLinkNode {
    DoubleLinkNode next;    // указатель на следующий элемент
    DoubleLinkNode previous;    // указатель на следующий элемент
    int data;            // данные

    public static DoubleLinkNode toString(DoubleLinkNode doubleLinkNode) {
        if (doubleLinkNode != null) {
            System.out.println(doubleLinkNode.data);
        }
        return null;
    }
}

class Link {
    private LinkNode first;       // указатель на первый элемент

    public void checkSpeed()
    {
        LinkNode current = first;
        long startTime = System.nanoTime();
        current = current.next;
        System.out.println((System.nanoTime() - startTime));
    }

    public LinkNode find(int key) // Поиск элемента с заданным ключом
    { // (предполагается, что список не пуст)
        LinkNode current = first; // Начиная с 'first'
        while (current.data != key) // Пока совпадение не найдено
        {
            if (current.next == null) // Если достигнут конец списка
                return null; // и совпадение не найдено
            else // Если еще остались элементы
                current = current.next; // Перейти к следующему элементу
        }
        return current; // Совпадение обнаружено
    }

    public LinkNode findByIndex(int index) // Поиск элемента по индексу
    { // (предполагается, что список не пуст)
        LinkNode current = first; // Начиная с 'first'
        int j = 0;
        while (j != index) // Пока совпадение не найдено
        {
            if (current.next == null) // Если достигнут конец списка
                return null; // и совпадение не найдено
            else // Если еще остались элементы
            {
                current = current.next; // Перейти к следующему элементу
                j++;
            }
        }
        return current; // Совпадение обнаружено
    }

    public LinkNode delete(int key) // Удаление элемента с заданным ключом
    { // (предполагается, что список не пуст)
        LinkNode current = first; // Поиск элемента
        LinkNode previous = first;
        while (current.data != key)
        {
            if (current.next == null)
                return null; // Элемент не найден
            else {
                previous = current; // Перейти к следующему элементу
                current = current.next;
            }
        } // Совпадение найдено
        if (current == first) // Если первый элемент,
            first = first.next; // изменить first
        else // В противном случае
            previous.next = current.next; // обойти его в списке
        return current;
    }

    public LinkNode add(int data)           //добавить спереди
    {
        LinkNode item = new LinkNode();  //создаём новый элемент
        item.data = data;              //инициализируем данные.
        // указатель на следующий элемент автоматически инициализируется как null
        if (first == null) {           //если список пуст
            first = item;       //то указываем ссылки начала и конца на новый элемент
        } else {
            item.next = first;          //иначе новый элемент теперь ссылается на "бывший" первый
            first = item;               //а указатель на первый элемент теперь ссылается на новый элемент
        }
        return item;
    }

    public void printList()                //печать списка
    {
        LinkNode current = this.first;       //получаем ссылку на первый элемент
        while (current != null)           //пока элемент существуе
        {
            System.out.print(current.data + " "); //печатаем его данные
            current = current.next;                     //и переключаемся на следующий
        }
        System.out.println();
    }
}

class DLink {
    private LinkNode first;       // указатель на первый элемент
    private LinkNode tail;        // указатель последний элемент

    public void checkSpeed()
    {
        LinkNode current = first;
        long startTime = System.nanoTime();
        current = current.next;
        System.out.println((System.nanoTime() - startTime));
        startTime = System.nanoTime();
        current = tail;
        System.out.println((System.nanoTime() - startTime));
    }

    public LinkNode find(int key) // Поиск элемента с заданным ключом
    { // (предполагается, что список не пуст)
        LinkNode current = first; // Начиная с 'first'
        while (current.data != key) // Пока совпадение не найдено
        {
            if (current.next == null) // Если достигнут конец списка
                return null; // и совпадение не найдено
            else // Если еще остались элементы
                current = current.next; // Перейти к следующему элементу
        }
        return current; // Совпадение обнаружено
    }

    public LinkNode findByIndex(int index) // Поиск элемента по индексу
    { // (предполагается, что список не пуст)
        LinkNode current = first; // Начиная с 'first'
        int j = 0;
        while (j != index) // Пока совпадение не найдено
        {
            if (current.next == null) // Если достигнут конец списка
                return null; // и совпадение не найдено
            else // Если еще остались элементы
            {
                current = current.next; // Перейти к следующему элементу
                j++;
            }
        }
        return current; // Совпадение обнаружено
    }

    public LinkNode delete(int key) // Удаление элемента с заданным ключом
    { // (предполагается, что список не пуст)
        LinkNode current = first; // Поиск элемента
        LinkNode previous = first;
        while(current.data != key)
        {
            if (current.next == null)
                return null; // Элемент не найден
            else {
                previous = current; // Перейти к следующему элементу
                current = current.next;
            }
        } // Совпадение найдено
        if (current == first) // Если первый элемент,
            first = first.next; // изменить first
        else // В противном случае
            previous.next = current.next; // обойти его в списке
        return current;
    }

    public LinkNode addFirst(int data)           //добавить спереди
    {
        LinkNode item = new LinkNode();  //создаём новый элемент
        item.data = data;              //инициализируем данные.
        // указатель на следующий элемент автоматически инициализируется как null
        if (first == null)            //если список пуст
        {                           //то указываем ссылки начала и конца на новый элемент
            first = item;               //т.е. список теперь состоит из одного элемента
            tail = item;
        }
        else {
            item.next = first;          //иначе новый элемент теперь ссылается на "бывший" первый
            first = item;               //а указатель на первый элемент теперь ссылается на новый элемент
        }
        return item;
    }

    public LinkNode addLast(int data) {          //добавление в конец списка
        LinkNode item = new LinkNode();  //создаём новый элемент
        item.data = data;
        if (tail == null)           //если список пуст
        {                           //то указываем ссылки начала и конца на новый элемент
            first = item;               //т.е. список теперь состоит из одного элемента
            tail = item;
        } else {
            tail.next = item;          //иначе "старый" последний элемент теперь ссылается на новый
            tail = item;               //а в указатель на последний элемент записываем адрес нового элемента
        }
        return item;
    }

    public void printList()                //печать списка
    {
        LinkNode current = this.first;       //получаем ссылку на первый элемент
        while (current != null)           //пока элемент существуе
        {
            System.out.print(current.data + " "); //печатаем его данные
            current = current.next;                     //и переключаемся на следующий
        }
        System.out.println();
    }
}

class DoubleLink {
    private DoubleLinkNode first;       // указатель на первый элемент
    private DoubleLinkNode tail;        // указатель последний элемент

    public void checkSpeed()
    {
        DoubleLinkNode current = first;
        long startTime = System.nanoTime();
        current = current.next;
        System.out.println((System.nanoTime() - startTime));
        startTime = System.nanoTime();
        current = current.previous;
        System.out.println((System.nanoTime() - startTime));
        startTime = System.nanoTime();
        current = tail;
        System.out.println((System.nanoTime() - startTime));
    }

    public DoubleLinkNode find(int key) // Поиск элемента с заданным ключом
    { // (предполагается, что список не пуст)
        DoubleLinkNode current = first; // Начиная с 'first'
        while (current.data != key) // Пока совпадение не найдено
        {
            if (current.next == null) // Если достигнут конец списка
                return null; // и совпадение не найдено
            else // Если еще остались элементы
                current = current.next; // Перейти к следующему элементу
        }
        return current; // Совпадение обнаружено
    }

    public DoubleLinkNode findByIndex(int index) // Поиск элемента по индексу
    { // (предполагается, что список не пуст)
        DoubleLinkNode current = first; // Начиная с 'first'
        int j = 0;
        while (j != index) // Пока совпадение не найдено
        {
            if (current.next == null) // Если достигнут конец списка
                return null; // и совпадение не найдено
            else // Если еще остались элементы
            {
                current = current.next; // Перейти к следующему элементу
                j++;
            }
        }
        return current; // Совпадение обнаружено
    }

    public DoubleLinkNode addFirst(int data)           //добавить спереди
    {
        DoubleLinkNode item = new DoubleLinkNode();  //создаём новый элемент
        item.data = data;              //инициализируем данные.
        // указатель на следующий элемент автоматически инициализируется как null
        if (first == null)            //если список пуст
        {                           //то указываем ссылки начала и конца на новый элемент
            tail = item;
        }
        else {
            first.previous = item;
            item.next = first; //а указатель на первый элемент теперь ссылается на новый элемент
        }
        first = item;
        return item;
    }

    public DoubleLinkNode addLast(int data)           //добавить сзади
    {
        DoubleLinkNode item = new DoubleLinkNode();  //создаём новый элемент
        item.data = data;              //инициализируем данные.
        // указатель на следующий элемент автоматически инициализируется как null
        if (first == null)            //если список пуст
        {                           //то указываем ссылки начала и конца на новый элемент
            first = item;
        }
        else {
            tail.next = item;
            item.previous = tail;               //а указатель на первый элемент теперь ссылается на новый элемент
        }
        tail = item;
        return item;
    }

    public DoubleLinkNode addAfter(int key, int data)
    {
        if (first == null)            //если список пуст
        {
            return null;
        }
        DoubleLinkNode current = first;
        while (current.data != key)
        {
            current = current.next;
            if (current == null)
                return null;
        }
        DoubleLinkNode item = new DoubleLinkNode();
        item.data = data;
        if (current == tail)
        {
            item.next = null;
            tail = item;
        } else
        {
            item.next = current.next;
            current.next.previous = item;
        }
        item.previous = current;
        current.next = item;
        return item;
    }

    public DoubleLinkNode deleteFirst() {
        DoubleLinkNode deleteItem = first;
        if (first.next == null) {
            tail = null;
        } else {
            first.next.previous = null;
        }
        first = first.next;
        return deleteItem;
    }

    public DoubleLinkNode deleteLast() {
        DoubleLinkNode deleteItem = tail;
        if (first.next == null) {
            first = null;
        } else {
            tail.previous.next = null;
        }
        tail = tail.previous;
        return deleteItem;
    }

    public DoubleLinkNode deleteKey(long key) // Удаление элемента с заданным ключом
    {
        if (first == null)
            return null;
        DoubleLinkNode current = first; // От начала списка
        while(current.data != key) // Пока не будет найдено совпадение
        {
            current = current.next; // Переход к следующему элементу
            if(current == null)
                return null; // Ключ не найден
        }
        if(current==first) // Ключ найден; это первый элемент?
            first = current.next; // first --> старое значение next
        else // Не первый элемент
// старое значение previous --> старое значение next
            current.previous.next = current.next;
        if(current==tail) // Последний элемент?
            tail = current.previous; // старое значение previous <-- last
        else // Не последний элемент
// Старое значение previous <-- старое значение next
            current.next.previous = current.previous;
        return current; // Возвращение удаленного элемента
    }

    void printList()                //печать списка
    {
        DoubleLinkNode current = this.first;       //получаем ссылку на первый элемент
        while (current != null)           //пока элемент существуе
        {
            System.out.print(current.data + " "); //печатаем его данные
            current = current.next;                     //и переключаемся на следующий
        }
        System.out.println();
    }
}

class Person {
    Person next;

    String name;
    String lastName;
}

class PersonLink {
    private Person first;

    public void insert(String name, String lastName){ // Вставка в порядке сортировки
        Person newLink = new Person(); // Создание нового элемента
        newLink.name = name;
        newLink.lastName = lastName;
        Person previous = null; // От начала списка
        Person current = first;
// До конца списка
        while(current != null && (newLink.name.compareTo(current.name)) >= 0) { // или если key > current,
            previous = current;
            current = current.next; // Перейти к следующему элементу
        }
        if(previous==null) // В начале списка
            first = newLink; // first --> newLink
        else // Не в начале
            previous.next = newLink; // старое значение prev --> newLink
        newLink.next = current; // newLink --> старое значение current
    }

    public void printList()                //печать списка
    {
        Person current = this.first;       //получаем ссылку на первый элемент
        while (current != null)           //пока элемент существуе
        {
            System.out.print(current.name + " " + current.lastName + " / "); //печатаем его данные
            current = current.next;                     //и переключаемся на следующий
        }
        System.out.println();
    }
}

public class zadanie4 {
    public static void main(String[] args) {
        Link l = new Link();
        DLink dl = new DLink();
        DoubleLink dbl = new DoubleLink();
        for(int i = 0; i<=10; i++){
            l.add(i);
            dl.addFirst(i);
            dbl.addFirst(i);
        }

        System.out.println("Связный список");
        l.printList();
        System.out.println("Найдено");
        LinkNode.toString(l.find(4));
        System.out.println("Найдено по индексу");
        LinkNode.toString(l.findByIndex(4));
        System.out.println("Удалено");
        LinkNode.toString(l.delete(4));
        System.out.println("Не найдено");
        LinkNode.toString(l.find(4));
        System.out.println("Что вышло");
        l.printList();
        System.out.println("Скорость");
        l.checkSpeed();

        System.out.println("------------------------------------------");

        System.out.println("Двусторонний список");
        dl.printList();
        System.out.println("Найдено");
        LinkNode.toString(dl.find(4));
        System.out.println("Найдено по индексу");
        LinkNode.toString(dl.findByIndex(4));
        System.out.println("Удалено");
        LinkNode.toString(dl.delete(4));
        System.out.println("Не найдено");
        LinkNode.toString(dl.find(4));
        System.out.println("Вставлено в конец");
        LinkNode.toString(dl.addLast(100));
        System.out.println("Что вышло");
        dl.printList();
        System.out.println("Скорость");
        dl.checkSpeed();

        System.out.println("------------------------------------------");

        System.out.println("Двусвязный список");
        dbl.printList();
        System.out.println("Найдено");
        DoubleLinkNode.toString(dbl.find(4));
        System.out.println("Найдено по индексу");
        DoubleLinkNode.toString(dbl.findByIndex(4));
        System.out.println("Удалено");
        DoubleLinkNode.toString(dbl.deleteKey(4));
        System.out.println("Удален первый элемент");
        DoubleLinkNode.toString(dbl.deleteFirst());
        System.out.println("Удален последний элемент");
        DoubleLinkNode.toString(dbl.deleteLast());
        System.out.println("Не найдено");
        DoubleLinkNode.toString(dbl.find(4));
        System.out.println("Вставлено в конец");
        DoubleLinkNode.toString(dbl.addLast(100));
        System.out.println("Вставлено после 2");
        DoubleLinkNode.toString(dbl.addAfter(2, 200));
        System.out.println("Что вышло");
        dbl.printList();
        System.out.println("Скорость");
        dbl.checkSpeed();

        System.out.println("------------------------------------------");

        PersonLink p = new PersonLink();
        p.insert("Roma", "Suchev");
        p.insert("Dima", "Komov");
        p.insert("Sasha", "Qoter");
        System.out.println("Сортированный список вставкой");
        p.printList();
    }
}