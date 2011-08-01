package de.sub.goobi.helper;

//TODO: Use generics
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class TreeNode {

   protected boolean expanded = false;
   protected boolean selected = false;
   protected String label;
   protected String id;
   protected List<TreeNode> children;

   public TreeNode() {
      this.children = new ArrayList<TreeNode>();
   }

   public TreeNode(boolean expanded, String label, String id) {
      this.expanded = expanded;
      this.label = label;
      this.id = id;
      this.children = new ArrayList<TreeNode>();
   }

   

   public void addChild(TreeNode inNode) {
      this.children.add(inNode);
   }

   

   public List<TreeNode> getChildrenAsList() {
      List<TreeNode> myList = new ArrayList<TreeNode>();
//            getChildrenAsList(myList, 0,new ArrayList(),false);
      getChildrenAsListMitStrichen(myList, 0, this, true,true, new ArrayList<TreeNode>());
      return myList;
   }

   

   public List<TreeNode> getChildrenAsListAlle() {
      List<TreeNode> myList = new ArrayList<TreeNode>();
      getChildrenAsListAlle(myList, 0, this, true,true, new ArrayList<TreeNode>());
      return myList;
   }
   
   /**
   	 * alle Children des übergebenen Knotens expanden oder collapsen 
* ================================================================
   */
   public void expandNodes(Boolean inExpand){
      expandNode(this, inExpand.booleanValue());
   }
   
   private void expandNode(TreeNode inNode, boolean inExpand){
      inNode.expanded = inExpand;
      for (Iterator<TreeNode> iter = inNode.children.iterator(); iter.hasNext();) {
         TreeNode t = iter.next();
         expandNode(t, inExpand);
      }
   }
   
   

   @SuppressWarnings({ "unused", "unchecked", "rawtypes" })
	private List getChildrenAsList(List inList, int niveau,List inStriche,boolean VaterIstLetzter) {
      for (Iterator<TreeNode> it = this.children.iterator(); it.hasNext();) {
         TreeNode kind = it.next();
         HashMap map = new HashMap();
         map.put("node", kind);
         map.put("niveau", Integer.valueOf(niveau));
         map.put("islast", Boolean.valueOf(!it.hasNext()));

//       die Striche vorbereiten
         List striche = new ArrayList(inStriche);
         striche.add(Boolean.valueOf(VaterIstLetzter));
         map.put("striche", striche);
         
         inList.add(map);
         if (kind.expanded && kind.getHasChildren()) {
			kind.getChildrenAsList(inList, niveau + 1, striche,!it.hasNext());
		}
      }
       return inList;
   }

   

	@SuppressWarnings({"unchecked", "rawtypes"} )
	private List getChildrenAsListMitStrichen(List inList, int niveau, TreeNode inNode, boolean istLetzter,
         boolean VaterIstLetzter, List inStriche) {

      HashMap map = new HashMap();
      map.put("node", inNode);
      map.put("niveau", Integer.valueOf(niveau));
      map.put("islast", Boolean.valueOf(istLetzter));

      // die Striche vorbereiten
      List striche = new ArrayList(inStriche);
      striche.add(Boolean.valueOf(VaterIstLetzter));
      map.put("striche", striche);

      inList.add(map);

      if (inNode.getHasChildren() && inNode.expanded) {
         for (Iterator it = inNode.getChildren().iterator(); it.hasNext();) {
            TreeNode kind = (TreeNode) it.next();
            getChildrenAsListMitStrichen(inList, niveau + 1, kind, !it.hasNext(), istLetzter, striche);
         }
      }

      return inList;
   }
   
   

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List getChildrenAsListAlle(List inList, int niveau, TreeNode inNode, boolean istLetzter,
         boolean VaterIstLetzter, List inStriche) {

      HashMap map = new HashMap();
      map.put("node", inNode);
      map.put("niveau", Integer.valueOf(niveau));
      map.put("islast", Boolean.valueOf(istLetzter));

      // die Striche vorbereiten
      List striche = new ArrayList(inStriche);
      striche.add(Boolean.valueOf(VaterIstLetzter));
      map.put("striche", striche);

      inList.add(map);

      if (inNode.getHasChildren()) {
         for (Iterator it = inNode.getChildren().iterator(); it.hasNext();) {
            TreeNode kind = (TreeNode) it.next();
            getChildrenAsListAlle(inList, niveau + 1, kind, !it.hasNext(), istLetzter, striche);
         }
      }

      return inList;
   }

   /*#####################################################
    #####################################################
    ##                                                                                              
    ##                                                Getter und Setter                         
    ##                                                                                                    
    #####################################################
    ####################################################*/

   public List<TreeNode> getChildren() {
      return this.children;
   }

	public void setChildren(List<TreeNode> children) {
      this.children = children;
   }

   public boolean isExpanded() {
      return this.expanded;
   }

   public void setExpanded(boolean expanded) {
      this.expanded = expanded;
   }

   public String getId() {
      return this.id;
   }

   public void setId(String id) {
      this.id = id;
   }
   
   public boolean isSelected() {
      return this.selected;
   }

   public void setSelected(boolean selected) {
      this.selected = selected;
   }

   public String getLabel() {
      return this.label;
   }

   public void setLabel(String label) {
      this.label = label;
   }

   public boolean getHasChildren() {
      if (this.children == null || this.children.size() == 0) {
		return false;
	} else {
		return true;
	}
   }

}
