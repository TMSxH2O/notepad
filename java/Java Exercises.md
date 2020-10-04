# 字符串

### Q：比较两个字符串中最长的相同子串

将两个字符串分别以行和列组成矩阵

- 构成的居正中，相同字符交汇处为1；反之为0
- 通过值为1的最长的对角线即可得到公共子串

由此思路进行优化

- 检查每个不为0的节点，加上其左上角节点的值（就可以遍历表得到最大值即可）

**特殊情况**

- 两个字符串本来就像等
- 长字符串本来就包含短字符串

```java
// 如，传递的参数为 "abcdef" 和"defg"
public static String getMaxSubString(String maxString, String minString) {
    //1. 必须保证 第一个字符串的长度是长的。第二个是短的。
    if (minString.length() > maxString.length()) {
        // 重新调用这个方法
        return getMaxSubString(minString, maxString);
    }
    //2. 判断一下，是否直接包含，如果是的话，就不用进行阵列转换了。
    if (maxString.contains(minString)) {
        return minString;
    }
    //3. 取出长度，转换相对应的矩阵。 通常，长的为y,短的为x.
    int maxLength = maxString.length();
    int minLength = minString.length();
    // 构建二维数组
    int[][] conver = new int[minLength][maxLength];
    int maxValue = 0; //最大的值。
    int maxIndex = 0; //最大的索引。
    //4. 对这个矩阵进行相应的放值。
    for (int i = 0; i < minLength; i++) {
        for (int j = 0; j < maxLength; j++) {
            //5.判断一下，值是否相同。 如果相同，
            if (minString.charAt(i) == maxString.charAt(j)) {
                //相同了，看是第几行，第几列。 第1行或者第1列的为1
                if (i == 0 || j == 0) {
                    conver[i][j] = 1;
                } else {
                    conver[i][j] = conver[i - 1][j - 1] + 1; //为左上角的值加1.
                    if (maxValue < conver[i][j]) { // 整个数组的最大值。 也可以是<= < 时表示取第一个，<=为最后一个。(如果存在多个的情况下)
                        maxValue = conver[i][j]; //取出那个最大的值。
                        maxIndex = i; //取出那个最大的列索引。
                    }
                }

            } else {
                conver[i][j] = 0;  //如果不相同，为0.
            }
        }

    }
    //5. 根据最大的索引和最大的值，来判断截取那个最大的子字符串。
    if (maxValue != 0 && maxIndex != 0) { // 双重判断，如果有值的话。
        // maxIndex = 3；maxValue = 3
        // 此时，希望取出其中的def子串，通过短的串，起始[maxIndex-maxValue+1, maxIndex+1)，即[1,4)
        return minString.substring( maxIndex - maxValue + 1, maxIndex + 1);
    }
    return null;
}
```

这样的代码存在问题，**如果又存在两个长度相同的最长字段，内容不同**。此时应该分别输出两个子串，然而此时只能输出其中一个。

```java
public static void getMaxSubString(String maxString, String minString) {
    if (minString.length() > maxString.length()) {
        getMaxSubString(minString, maxString);
        return;
    }
    if (maxString.contains(minString)) {
        System.out.println(minString);
        return;
    }
    int maxLength = maxString.length();
    int minLength = minString.length();
    int[][] conver = new int[minLength][maxLength];
    int maxValue = 0;
    int maxIndex = 0;
    // 存放不同的最大字串
    List<Integer> maxIndexList = new LinkedList<>();

    for (int i = 0; i < minLength; i++) {
        for (int j = 0; j < maxLength; j++) {
            if (minString.charAt(i) == maxString.charAt(j)) {
                if (i == 0 || j == 0) {
                    conver[i][j] = 1;
                } else {
                    conver[i][j] = conver[i - 1][j - 1] + 1; 
                    if (maxValue < conver[i][j]) {
                        maxValue = conver[i][j]; 
                        maxIndex = i; 
                        maxIndexList.clear();
                    } else if (maxValue == conver[i][j]) {
                        boolean isVale = true;
                        for (int index : maxIndexList) {
                            if (minString.substring(index - maxValue + 1, index + 1).equals(
                                minString.substring(i - maxValue + 1, i + 1))) {
                                isVale = false;
                                break;
                            }
                        }
                        if (isVale)
                            maxIndexList.add(i);
                    }
                }

            } else {
                conver[i][j] = 0;
            }
        }
    }
    if (maxValue != 0 && !maxIndexList.isEmpty()) { 
        for (int i : maxIndexList)
            System.out.println(minString.substring(i - maxValue + 1, i + 1));
    }
}
```



### Q：如下代码的输出

```java
String str = null;
StringBuffer sb = new StringBuffer();
sb.append(str);

System.out.println(sb.length());//4
System.out.println(sb);// "null"

StringBuffer sb1 = new StringBuffer(str); // 抛出异常NullPointerException
System.out.println(sb1); 
```

会抛出异常的原因，StringBuffer的构造器中，直接调用了父类的构造器。

```java
AbstractStringBuilder(String str) {
    int length = str.length(); // 此处直接使用了length方法（null不能调用方法）
    int capacity = (length < Integer.MAX_VALUE - 16)
        ? length + 16 : Integer.MAX_VALUE;
    final byte initCoder = str.coder();
    coder = initCoder;
    value = (initCoder == LATIN1)
        ? new byte[capacity] : StringUTF16.newBytesFor(capacity);
    append(str);
}
```

而append中也是调用的父类append方法

```java
public AbstractStringBuilder append(String str) {
    if (str == null) { // 插入对象为null时的操作
        return appendNull();
    }
    int len = str.length();
    ensureCapacityInternal(count + len);
    putStringAt(count, str);
    count += len;
    return this;
}
// 添加null，将其按照“null”进行插入
private AbstractStringBuilder appendNull() {
    ensureCapacityInternal(count + 4); // 确认内存
    int count = this.count;
    byte[] val = this.value;
    if (isLatin1()) {
        val[count++] = 'n';
        val[count++] = 'u';
        val[count++] = 'l';
        val[count++] = 'l';
    } else {
        count = StringUTF16.putCharsAt(val, count, 'n', 'u', 'l', 'l');
    }
    this.count = count;
    return this;
}
```

