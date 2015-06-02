using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WebApplication1.Models
{
    public class Index
    {
        public string Name { get; set; }
        public bool Primary { get; set; }
        public List<string> Fields { get; set; }
        public object IndexType { get; set; }

        public override string ToString()
        {
            StringBuilder sb = new StringBuilder("Index{");
            sb.Append("name='").Append(Name).Append('\'');
            sb.Append(", primary=").Append(Primary);
            sb.Append(", fields=").Append(String.Join(",", Fields));
            sb.Append(", indexType=").Append(IndexType);
            sb.Append('}');
            return sb.ToString();
        }

        public override bool Equals(Object obj)
        {
            // Check for null values and compare run-time types.
            if (obj == null || GetType() != obj.GetType())
                return false;

            Index index = (Index)obj;

            if (Primary != index.Primary) return false;

            if (Name != null ? !Name.Equals(index.Name) : index.Name != null) return false;

            // Probably incorrect - comparing Object[] arrays with Arrays.equals
            if (!Fields.SequenceEqual(index.Fields)) return false;

            return IndexType == index.IndexType;
        }

        public override int GetHashCode()
        {
            int result = Name != null ? Name.GetHashCode() : 0;
            result = 31 * result + (Primary ? 1 : 0);
            result = 31 * result + (Fields != null ? Fields.GetHashCode() : 0);
            result = 31 * result + (IndexType != null ? IndexType.GetHashCode() : 0);
            return result;
        }
    }
}
