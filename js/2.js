

class Disorder {
  constructor(nElems) {
    this.nElems = nElems;
    this.a = new Array(this.nElems);
    for (let i = 0; i < this.nElems; i++) {
      this.a[i] = Math.round(Math.random() * this.nElems)
    }
  }

  get () {
    return this.a;
  }

  insertSort (items) {
    let temp,
      j;

    for (let i = 1; i < items.length; i++) {
      temp = items[i];

      for (j = i - 1; j >= 0; j--) {
        if (items[j] > temp) {
          items[j + 1] = items[j];
        } else {
          break;
        }
      }

      items[j + 1] = temp;
    }

    return items
  }

  choiceSort (items) {
    let min,
      nmin,
      temp;

    items.forEach((e, index) => {
      min = items[index];
      nmin = index;

      for (let k = index + 1; k < items.length; k++) {
        if (items[k] < min) {
          min = items[k];
          nmin = k;
        }
      }

      temp = items[index];
      items[index] = min;
      items[nmin] = temp
    })

    return items;
  }

  static merge = (arrFirst, arrSecond) => {
    const arrSort = [];
    let i, j = 0;

    while (arrFirst.length && arrSecond.length) {
      arrSort.push(
        (arrFirst[0] < arrSecond[0]) ?
          arrFirst.shift() : arrSecond.shift()
      );
    }

    return [
      ...arrSort,
      ...arrFirst.slice(i),
      ...arrSecond.slice(j)
    ];
  };

  mergeSort (items) {
    return Disorder.mSort(items)
  }

  static mSort (arr) {
    if (!arr || !arr.length) {
      return null;
    }

    if (arr.length <= 1) {
      return arr;
    }

    const middle = Math.floor(arr.length / 2);
    const arrLeft = arr.slice(0, middle);
    const arrRight = arr.slice(middle);

    return Disorder.merge(Disorder.mSort(arrLeft), Disorder.mSort(arrRight));
  };

  static swap (items, firstIndex, secondIndex) {
    const temp = items[firstIndex];
    items[firstIndex] = items[secondIndex];
    items[secondIndex] = temp;
  }

  static partition (items, left, right) {
    let pivot = items[Math.floor((right + left) / 2)],
      i = left,
      j = right;

    while (i <= j) {
      while (items[i] < pivot) {
        i++;
      }

      while (items[j] > pivot) {
        j--;
      }

      if (i <= j) {
        Disorder.swap(items, i, j);
        i++;
        j--;
      }
    }

    return i;
  }

  quickSort (items) {
    return Disorder.qSort(items);
  }

  static qSort (items, left, right) {
    let index;

    if (items.length > 1) {
      left = typeof left != "number" ? 0 : left;
      right = typeof right != "number" ? items.length - 1 : right;

      index = Disorder.partition(items, left, right);

      if (left < index - 1) {
        Disorder.qSort(items, left, index - 1);
      }

      if (index < right) {
        Disorder.qSort(items, index, right);
      }
    }

    return items;
  }
}

const ss = new Disorder(10)
console.log(ss.a)
console.log('Вставка')
console.log(ss.insertSort(ss.get()))
console.log('Выбор')
console.log(ss.choiceSort(ss.get()))
console.log('Слияние')
console.log(ss.mergeSort(ss.get()))
console.log('Быстрая')
console.log(ss.quickSort(ss.get()))