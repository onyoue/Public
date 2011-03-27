class Tree
  attr_accessor :children, :node_name
  
  def initialize(name, children=[])
    @children = children
    @node_name = name
  end
  
  def initialize(name, tree={})
    @node_name = name
    @children = []
    tree.each {|k,d| @children.push Tree.new(k, d)}
  end
  
  def visit_all(&block)
    visit &block
    children.each {|c| c.visit_all &block}
  end
  
  def visit(&block)
    block.call self
  end
end

# ruby_tree = Tree.new( "Ruby",
#   [Tree.new("Reia"),
#     Tree.new("MacRuby")])
    
# puts "Visiting a node"
# ruby_tree.visit {|node| puts node.node_name}
# puts

# puts "visiting entire tree"
# ruby_tree.visit_all {|node| puts node.node_name}

tree_data = {'grandpa' => { 'dad' => {'child 1' => {},
                                                  'child 2' => {}},
                                                  'uncle' => {'child 3' => {},
                                                              'child 4' => {}}}}
                                                              
ruby_tree2 = Tree.new( 'root', tree_data )

ruby_tree2.visit_all {|node| puts node.node_name}