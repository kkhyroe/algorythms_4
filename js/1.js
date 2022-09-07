class Disorder {
  constructor(nElems) {
    this.nElems = nElems;
    this.a = new Array(nElems)
  }

  find (searchKey) {
    let value = -1;

    this.a.forEach(e => {
      if (e === searchKey) {
        value = e
      }
    })

    return value;
  }

  insert (value) {
    this.a[this.nElems] = value;
    this.nElems += 1;
  }

  delete (value) {
    this.a.forEach((e, index) => {
      if (e === value) {
        this.a.splice(index, 1);
        this.nElems -= 1;
      }
    })
  }
}

class Order {
  constructor(nElems) {
    this.nElems = nElems;
    this.a = new Array(nElems)
  }

  find (searchKey) {
    let low = 0,
      high = this.nElems - 1,
      pos = -1,
      found = false,
      mid;

    while (found === false && low <= high) {
      mid = (low + high) >>> 1;

      if (this.a[mid] === searchKey) {
          found = true;
          pos = mid;
      } else if (this.a[mid] > searchKey) {
        high = mid - 1;
      } else {
        low = mid + 1
      }
    }

    return pos;
  }

  insert (value) {
    for (var i = 0; i < this.nElems; i++) {
      if (this.a[i] > value)
        break
    }

    for (let k = this.nElems; k > i; k--) {
      this.a[k] = this.a[k-1];
    }

    this.a[i] = value;
    this.nElems += 1;
  }

  delete (value) {
    let j = this.find(value)

    if (j === this.nElems) {
      return false;
    } else {
      for (let k = j; k < this.nElems; k++) {
        this.a[k] = this.a[k+1];
      }

      this.nElems--;
      this.a.pop()
      return true
    }
  }
}

const ss = new  Disorder(10)
ss.insert(0)
ss.insert(2)
console.log(ss.a)
ss.delete(0)
console.log(ss.a)
console.log(ss.find(0))
console.log(ss.find(2))
console.log(ss.a)


const zz = new Order(10)
zz.insert(25)
zz.insert(1)
console.log(zz.a)
zz.delete(1)
console.log(zz.a)
console.log(zz.find(1))
console.log(zz.find(25))