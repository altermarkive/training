package cavitymap

import (
	"bufio"
	"bytes"
	"fmt"
	"io"
	"os"
	"strconv"
	"strings"
	"testing"
)

func Runner(t *testing.T, name string) {
	ioLines := make([][][]string, 2)
	for index, template := range []string{"input%s.txt", "output%s.txt"} {
		path := fmt.Sprintf(template, name)
		file, fail := os.Open(path)
		if fail != nil {
			t.Fatalf("Failed opening file %s: %s", path, fail)
		}
		defer file.Close()
		lines := make([][]string, 0)
		ioLines[index] = lines
		reader := bufio.NewReader(file)
		for {
			var buffer bytes.Buffer
			var raw []byte
			var prefix bool
			for {
				raw, prefix, fail = reader.ReadLine()
				buffer.Write(raw)
				if !prefix || fail != nil {
					break
				}
			}
			ioLines[index] = append(ioLines[index], strings.Split(strings.TrimSpace(buffer.String()), " "))
			if fail == io.EOF {
				break
			} else if fail != nil {
				t.Fatalf("Failed reading file %s: %s", path, fail)
			}
		}
	}
	h, _ := strconv.ParseInt(ioLines[0][0][0], 10, 32)
	m, _ := strconv.ParseInt(ioLines[0][1][0], 10, 32)
	result := TimeInWords(int32(h), int32(m))
	expected := strings.Join(ioLines[1][0], " ")
	if result != expected {
		t.Errorf("Failed by returning wrong value - '%s' instead of '%s'!", result, expected)
	}
}

func TestExample0(t *testing.T) {
	Runner(t, "_example_0")
}

func TestExample1(t *testing.T) {
	Runner(t, "_example_1")
}

func TestExample2(t *testing.T) {
	Runner(t, "_example_2")
}

func Test0359(t *testing.T) {
	result := TimeInWords(3, 59)
	if "one minute to four" != result {
		t.Errorf("Failed by returning wrong value - '%s' instead of 'one minute to four'!", result)
	}
}

func Test0301(t *testing.T) {
	result := TimeInWords(3, 1)
	if "one minute past three" != result {
		t.Errorf("Failed by returning wrong value - '%s' instead of 'one minute past three'!", result)
	}
}

func Test0345(t *testing.T) {
	result := TimeInWords(3, 45)
	if "quarter to four" != result {
		t.Errorf("Failed by returning wrong value - '%s' instead of 'quarter to four'!", result)
	}
}

func Test0330(t *testing.T) {
	result := TimeInWords(3, 30)
	if "half past three" != result {
		t.Errorf("Failed by returning wrong value - '%s' instead of 'half past three'!", result)
	}
}

func Test0320(t *testing.T) {
	result := TimeInWords(3, 20)
	if "twenty minutes past three" != result {
		t.Errorf("Failed by returning wrong value - '%s' instead of 'twenty minutes past three'!", result)
	}
}
